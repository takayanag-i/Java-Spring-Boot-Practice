package com.example.demo.dao;

import com.example.demo.model.Course;
import com.example.demo.model.Instruction;
import com.example.demo.dto.SearchCriteriaDto;
import com.example.demo.repository.InstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstructionDao {

    private final InstructionRepository instructionRepository;

    @Transactional(readOnly = true)
    public List<Instruction> select(List<Course> courseEntities)
            throws DataAccessException {
        List<String> courseIds = courseEntities.stream()
                .map(Course::getCourseId)
                .collect(Collectors.toList());
        return instructionRepository.findByCourseIdIn(courseIds);
    }

    @Transactional(readOnly = true)
    public List<Instruction> select(SearchCriteriaDto criteriaDto)
            throws DataAccessException {
        return instructionRepository
                .findByInstructorNameContaining(criteriaDto.getInstructorName());
    }
}
