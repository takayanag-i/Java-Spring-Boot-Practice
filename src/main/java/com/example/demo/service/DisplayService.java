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

/**
 * 時間割表示機能のサービスクラス
 */
@Service
@RequiredArgsConstructor
public class DisplayService {

    /** リポジトリ */
    private final TimeRepository timeRepository;

    /**
     * 与えられた生徒の時間割をマトリクス形式で取得する
     * 
     * @param studentId 対象生徒の出席番号
     * @return 対象生徒の時間割を表す {@link CourseDto} の2x2マトリクス
     */
    public List<List<CourseDto>> getCourseMatrix(String studentId) {
        // 空のCourseDtoでマトリクスを初期化する
        List<List<CourseDto>> matrix = this.initialiseMatrix();

        // 対象生徒の時間割をまずCourseDtoのリスト形式で取得する
        List<CourseDto> courses = this.getCourseDtos(studentId);

        // 曜日・時限をみてマトリクスのCourseDtoを置き換える
        for (CourseDto course : courses) {
            course.setFormAction("PreDeleteServlet");

            int day = course.getDayOfWeek().getInt();
            int period = Integer.parseInt(course.getPeriod());
            matrix.get(day - 1).set(period - 1, course);
        }

        // 連続時限開講の講座用にセルを結合する処理を行う
        for (int i = 0; i < matrix.size(); i++) {
            List<CourseDto> vector = matrix.get(i);
            List<CourseDto> newVector = this.consolidateConsecutiveCourses(vector);

            matrix.set(i, newVector);
        }

        // 転置して返却
        return MatrixUtil.transpose(matrix);
    }

    /**
     * デフォルト値の入った {@link CourseDto} でマトリクスを初期化する
     * 
     * @return 初期化されたマトリクス
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
     * 連続時限で開講する講座をセル結合して表示するために，rowspan属性の値を更新する
     * 
     * @param vector 結合操作対象の {@link CourseDto} ベクトル（曜日ごと）
     * @return 結合処理された {@link CourseDto} ベクトル（曜日ごと）
     */
    private List<CourseDto> consolidateConsecutiveCourses(List<CourseDto> vector) {
        Map<String, CourseDto> map = new LinkedHashMap<>();

        for (CourseDto dto : vector) {
            String courseId = dto.getCourseId();

            if (courseId.isEmpty()) {
                // 空のセル
                map.put(UUID.randomUUID().toString(), dto);
                continue;
            }

            if (map.containsKey(courseId)) {
                // 連続時限で開講されている場合の処理
                CourseDto duplicateCourse = map.get(courseId);
                duplicateCourse.setRowspan(duplicateCourse.getRowspan() + 1);

            } else {
                // それ以外 => 新たなエントリとして追加
                map.put(courseId, dto);

            }
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 与えられた出席番号の生徒が登録している講座 {@link CourseDto} のリストをDBから取得する
     *
     * @param studentId 対象生徒の出席番号
     * @return 対象生徒が登録している講座 {@link CourseDto} のリスト
     * @throws RuntimeException DB接続時の予期しないエラーは実行時例外として再スローする
     */
    @Transactional(readOnly = true)
    private List<CourseDto> getCourseDtos(String studentId) {
        List<CourseDto> courseDtos = new ArrayList<>();

        try {
            // 対象生徒の登録している講座をコマエンティティのリストとして取得
            List<Time> times =
                    timeRepository.findByCourse_Enrollments_StudentId(studentId);

            // コマからCourseDtoに変換する
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
