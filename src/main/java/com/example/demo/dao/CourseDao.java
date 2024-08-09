package com.example.demo.dao;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.dto.SearchCriteriaDto;
import com.example.demo.spec.CourseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseDao {

    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<Course> selectByCriteria(SearchCriteriaDto criteria)
            throws DataAccessException {
        CourseSpecification spec = new CourseSpecification(criteria);
        return courseRepository.findAll(spec);
    }
}
