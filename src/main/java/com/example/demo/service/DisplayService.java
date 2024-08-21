package com.example.demo.service;

import com.example.demo.dto.CourseDto;
import com.example.demo.model.Course;
import com.example.demo.model.Time;
import com.example.demo.repository.TimeRepository;
import com.example.demo.util.MatrixUtil;
import com.example.demo.common.DayOfWeek;
import com.example.demo.constants.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisplayService {

    /** repository */
    private final TimeRepository timeRepository;

    /**
     * Generates a matrix representing the course schedule for a given student.
     * 
     * @param studentId the ID of the student whose course matrix is to be generated
     * @return a matrix of {@link CourseDto} objects representing the student's course schedule
     */
    public List<List<CourseDto>> getCourseMatrix(String studentId) {
        // Initialize the course matrix with empty CourseDto objects
        List<List<CourseDto>> matrix = this.initialiseMatrix();

        // Retrieve the list of courses for the student and convert them to CourseDto objects
        List<CourseDto> courses = this.getCourseDtos(studentId);

        // Populate the matrix with the CourseDto objects for each day and period
        for (CourseDto course : courses) {
            course.setFormAction("PreDeleteServlet");

            int day = course.getDayOfWeek().getInt();
            int period = Integer.parseInt(course.getPeriod());
            matrix.get(day - 1).set(period - 1, course);
        }

        // Process the matrix to consolidate consecutive courses (merge cells)
        for (int i = 0; i < matrix.size(); i++) {
            List<CourseDto> vector = matrix.get(i);
            List<CourseDto> newVector = this.consolidateConsecutiveCourses(vector);

            matrix.set(i, newVector);
        }

        // Transpose the matrix to switch rows and columns before returning
        return MatrixUtil.transpose(matrix);
    }

    /**
     * Initialises a matrix of {@link CourseDto} objects with default values
     * 
     * @return initialised matrix
     */
    private List<List<CourseDto>> initialiseMatrix() {
        List<List<CourseDto>> matrix = new ArrayList<>();

        for (int day = 0; day < 5; day++) {
            List<CourseDto> vector = new ArrayList<>();
            for (int period = 0; period < 5; period++) {
                CourseDto emptyDto = new CourseDto()
                        .setCourseId("")
                        .setDayOfWeek(DayOfWeek.fromNum(day + 1))
                        .setPeriod(String.valueOf(period + 1))
                        .setFormAction("SearchServlet");
                vector.add(emptyDto);
            }
            matrix.add(vector);
        }

        return matrix;
    }

    /**
     * Consolidates consecutive courses in the given list by combining them into a single
     * {@link CourseDto} entry with an updated rowspan value.
     * 
     * @param vector the list of {@link CourseDto} objects to consolidate
     * @return a list of {@link CourseDto} objects with consolidated consecutive courses
     */
    private List<CourseDto> consolidateConsecutiveCourses(List<CourseDto> vector) {
        Map<String, CourseDto> map = new LinkedHashMap<>();

        for (CourseDto dto : vector) {
            String courseId = dto.getCourseId();

            if (courseId.isEmpty()) {
                // empty cells
                map.put(UUID.randomUUID().toString(), dto);
                continue;
            }

            if (map.containsKey(courseId)) {
                // consecutive courses
                CourseDto duplicateCourse = map.get(courseId);
                duplicateCourse.setRowspan(duplicateCourse.getRowspan() + 1);

            } else {
                // other => new entry
                map.put(courseId, dto);

            }
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Retrieves a list of {@link CourseDto} objects for a given student ID.
     *
     * @param studentId the ID of the student whose course details are to be retrieved
     * @return a list of {@link CourseDto} objects containing the course details for the student
     * @throws RuntimeException if there is an issue accessing the database
     */
    @Transactional(readOnly = true)
    private List<CourseDto> getCourseDtos(String studentId) {
        List<CourseDto> courseDtos = new ArrayList<>();

        try {
            // Retrieve the time entries with the given student ID
            List<Time> times =
                    timeRepository.findByCourse_Enrollments_StudentId(studentId);

            // Build the CourseDto object
            for (Time time : times) {
                Course course = time.getCourse();
                List<String> instructorNames = course.getInstructions().stream()
                        .map(instruction -> instruction.getInstructor().getName())
                        .collect(Collectors.toList());

                CourseDto courseDto = new CourseDto()
                        .setCourseId(course.getCourseId())
                        .setCourseName(course.getCourseName())
                        .setDayOfWeek(DayOfWeek.fromNum(time.getDayOfWeek()))
                        .setPeriod(time.getPeriod())
                        .setInstructorNames(instructorNames);

                courseDtos.add(courseDto);
            }

        } catch (DataAccessException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_DISPLAY_ERROR, e);
        }

        return courseDtos;
    }
}
