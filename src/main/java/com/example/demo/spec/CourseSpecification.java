package com.example.demo.spec;

import com.example.demo.model.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import com.example.demo.dto.SearchCriteriaDto;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification implements Specification<Course> {

        private final SearchCriteriaDto criteria;

        public CourseSpecification(SearchCriteriaDto criteria) {
                this.criteria = criteria;
        }

        @Override
        public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query,
                        CriteriaBuilder cb) {
                Predicate courseIdPredicate =
                                cb.like(root.get("courseId"), "%" + criteria.getCourseId() + "%");
                Predicate courseNamePredicate =
                                cb.like(root.get("courseName"),
                                                "%" + criteria.getCourseName() + "%");
                Predicate dayOfWeekPredicate =
                                cb.like(root.get("dayOfWeek"),
                                                "%" + criteria.getDayOfWeek().getNum() + "%");
                Predicate periodPredicate =
                                cb.like(root.get("period"), "%" + criteria.getPeriod() + "%");

                return cb.and(courseIdPredicate, courseNamePredicate, dayOfWeekPredicate,
                                periodPredicate);
        }
}
