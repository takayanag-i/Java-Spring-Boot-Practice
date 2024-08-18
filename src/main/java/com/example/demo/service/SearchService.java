package com.example.demo.service;

import com.example.demo.dto.CourseDto;
import com.example.demo.dto.SearchCriteriaDto;
import com.example.demo.model.Course;
import com.example.demo.model.Instruction;
import com.example.demo.model.Timetable;
import com.example.demo.repository.InstructionRepository;
import com.example.demo.repository.TimetableRepository;
import com.example.demo.logic.MultipleInstructorsLogic;
import com.example.demo.constants.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final TimetableRepository timeTableRepository;
    private final InstructionRepository instructionRepository;
    private final MultipleInstructorsLogic multipleInstructorsLogic;

    @Transactional(readOnly = true)
    public List<CourseDto> getCourses(SearchCriteriaDto criteriaDto) {
        List<CourseDto> courseDtos = new ArrayList<>();

        try {
            // 検索条件に基づく時間割エンティティの取得
            List<Timetable> timeTableEntities = timeTableRepository.findByCriteria(
                    "%" + criteriaDto.getCourseId() + "%",
                    "%" + criteriaDto.getCourseName() + "%",
                    "%" + criteriaDto.getDayOfWeek().getNum() + "%",
                    "%" + criteriaDto.getPeriod() + "%");

            // 講座リストの作成
            List<Course> courses = timeTableEntities.stream()
                    .map(timeTableEntity -> timeTableEntity.getCourse())
                    .distinct()
                    .collect(Collectors.toList());

            // 講座・教員対応エンティティの取得
            List<Instruction> instructionEntities =
                    instructionRepository.findByCourseIn(courses);

            // 講座DTOの作成
            for (Timetable timeTableEntity : timeTableEntities) {
                CourseDto courseDto =
                        multipleInstructorsLogic.merge(timeTableEntity, instructionEntities);
                if (courseDto != null) {
                    courseDtos.add(courseDto);
                }
            }

        } catch (DataAccessException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_SEARCH_ERROR, e);
        }

        return courseDtos;
    }
}
