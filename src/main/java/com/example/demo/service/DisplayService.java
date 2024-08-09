package com.example.demo.service;

import com.example.demo.dto.CourseDto;
import com.example.demo.model.Course;
import com.example.demo.model.Instruction;
import com.example.demo.model.Timetable;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.InstructionRepository;
import com.example.demo.repository.TimeTableRepository;
import com.example.demo.logic.MultipleInstructorsLogic;
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

    private final CourseRepository courseRepository;
    private final InstructionRepository instructionRepository;
    private final TimeTableRepository timeTableRepository;
    private final MultipleInstructorsLogic multipleInstructorsLogic;

    @Transactional(readOnly = true)
    public List<List<CourseDto>> getCourseMatrix(List<CourseDto> courses) {
        // 空きコマ用のDTOで初期化されたmatrixを生成
        List<List<CourseDto>> matrix = this.initialiseMatrix();

        // matrixにCourseDTOをセットする
        for (CourseDto course : courses) {
            course.setFormAction("PreDeleteServlet");

            int day = course.getDayOfWeek().getInt();
            int period = Integer.parseInt(course.getPeriod());
            matrix.get(day - 1).set(period - 1, course);
        }

        // セル結合のための処理
        for (int i = 0; i < matrix.size(); i++) {
            List<CourseDto> vector = matrix.get(i);
            List<CourseDto> newVector = this.consolidateConsecutiveCourses(vector);

            // matrixの行を更新
            matrix.set(i, newVector);
        }

        return MatrixUtil.transpose(matrix);
    }

    private List<List<CourseDto>> initialiseMatrix() {
        List<List<CourseDto>> matrix = new ArrayList<>();

        for (int i = 0; i < 5; i++) { // day
            List<CourseDto> vector = new ArrayList<>();
            for (int j = 0; j < 5; j++) { // period
                CourseDto emptyDto = new CourseDto();
                emptyDto.setCourseId("");
                emptyDto.setDayOfWeek(DayOfWeek.fromNum(i + 1));
                emptyDto.setPeriod(String.valueOf(j + 1));
                emptyDto.setFormAction("SearchServlet");
                vector.add(emptyDto);
            }
            matrix.add(vector);
        }

        return matrix;
    }

    private List<CourseDto> consolidateConsecutiveCourses(List<CourseDto> vector) {
        Map<String, CourseDto> map = new LinkedHashMap<>();

        for (CourseDto dto : vector) {
            String courseId = dto.getCourseId();

            if (courseId.isEmpty()) {
                map.put(UUID.randomUUID().toString(), dto); // 空のセルもマップに追加
                continue;
            }

            if (map.containsKey(courseId)) {
                CourseDto val = map.get(courseId);
                val.setRowspan(val.getRowspan() + 1);
            } else {
                map.put(courseId, dto);
            }
        }

        return new ArrayList<>(map.values());
    }

    public List<CourseDto> getCourses(String studentId) {
        List<CourseDto> courseDtos = new ArrayList<>();

        try {
            // 講座エンティティの取得
            List<Course> courseEntities =
                    courseRepository.findByEnrollments_StudentId(studentId);
            List<String> courseIds = courseEntities.stream().map(Course::getCourseId)
                    .collect(Collectors.toList());

            // 時間割エンティティの取得
            List<Timetable> timeTableEntities =
                    timeTableRepository.findByCourseIdIn(courseIds);

            // 講座・教員対応エンティティの取得
            List<Instruction> instructionEntities =
                    instructionRepository.findByCourseIdIn(courseIds);

            // 講座DTOの作成
            for (Timetable timeTableEntity : timeTableEntities) {
                CourseDto courseDto =
                        multipleInstructorsLogic.merge(timeTableEntity, instructionEntities);
                if (courseDto != null) {
                    courseDtos.add(courseDto);
                }
            }

        } catch (DataAccessException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_DISPLAY_ERROR, e);
        }

        return courseDtos;
    }
}
