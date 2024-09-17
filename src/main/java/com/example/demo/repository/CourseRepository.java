package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.demo.model.Course;

/**
 * 講座 {@link Course} エンティティに対するリポジトリ
 */
public interface CourseRepository
        extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {

    /**
     * 与えられた講座IDから講座を取得する
     *
     * @param courseId 検索対象の講座ID
     * @return 講座エンティティ
     */
    List<Course> findByCourseId(String courseId); // TODO 1個だけでは？

    /**
     * 与えられた出席番号の生徒が登録している講座のリストを取得する
     *
     * @param studentId 検索対象の生徒の出席番号
     * @return 生徒が登録している講座のリスト
     */
    List<Course> findByEnrollments_StudentId(String studentId);
}
