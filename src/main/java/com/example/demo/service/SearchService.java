package com.example.demo.service;

import com.example.demo.dto.CourseDto;
import com.example.demo.dto.SearchCriteriaDto;
import com.example.demo.logic.MultipleInstructorsLogic;
import com.example.demo.model.Course;
import com.example.demo.model.Instruction;
import com.example.demo.model.Time;
import com.example.demo.repository.InstructionRepository;
import com.example.demo.repository.TimeRepository;
import com.example.demo.constants.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 講座検索機能のサービスクラス
 */
@Service
@RequiredArgsConstructor
public class SearchService {

    /** コマリポジトリ */
    private final TimeRepository timeRepository;

    /** 講座担当リポジトリ */
    private final InstructionRepository instructionRepository;

    /** 複数教員対応ロジック */
    private final MultipleInstructorsLogic multipleInstructorsLogic;

    @Transactional(readOnly = true)
    public List<CourseDto> getCourses(SearchCriteriaDto criteria) {

        try {
            // 検索条件に基づきコマエンティティを取得する
            List<Time> times = timeRepository.findByCriteria(
                    "%" + criteria.getCourseId() + "%",
                    "%" + criteria.getCourseName() + "%",
                    "%" + criteria.getDayOfWeek() + "%",
                    "%" + criteria.getPeriod() + "%");

            // 講座リストの作成
            List<Course> courses = times.stream()
                    .map(time -> time.getCourse())
                    .distinct()
                    .collect(Collectors.toList());

            // 講座・教員対応エンティティの取得
            List<Instruction> instructions =
                    instructionRepository.findByCourseIn(courses);

            // 講座DTOの作成
            List<CourseDto> courseDtos = new ArrayList<>();

            for (Time time : times) {

                CourseDto courseDto =
                        multipleInstructorsLogic.merge(time, instructions);
                if (courseDto != null) {
                    courseDtos.add(courseDto);
                }
            }

            return courseDtos;

        } catch (DataAccessException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_SEARCH_ERROR, e);
        }
    }
}
