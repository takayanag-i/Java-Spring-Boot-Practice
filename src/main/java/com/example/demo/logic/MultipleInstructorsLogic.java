package com.example.demo.logic;

import com.example.demo.common.DayOfWeek;
import com.example.demo.dto.CourseDto;
import com.example.demo.model.Instruction;
import com.example.demo.model.Timetable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 複数教員情報をもつ講座DTOを作成するためのクラス
 */
@Component
public class MultipleInstructorsLogic {

    /**
     * 表示用の講座オブジェクトを取得する
     *
     * @param timeTableEntity 時間割エンティティ（教員情報なし）
     * @param instructionEntities 講座・教員対応エンティティのリスト
     * @return 講座オブジェクト
     */
    public CourseDto merge(Timetable timeTableEntity,
            List<Instruction> instructionEntities) {
        Map<String, List<String>> instructionMap = this.getInstructionMap(instructionEntities);
        List<String> instructors = instructionMap.get(timeTableEntity.getCourse().getCourseId());

        if (instructors == null) {
            // 教員名で検索があった場合などは
            // 講座エンティティの数 > 講座・教員対応エンティティの数なので
            return null;
        }

        CourseDto d = new CourseDto();
        d.setDayOfWeek(DayOfWeek.fromNum(Integer.parseInt(timeTableEntity.getDayOfWeek())));
        d.setPeriod(timeTableEntity.getPeriod());
        d.setCourseId(timeTableEntity.getCourse().getCourseId());
        d.setCourseName(timeTableEntity.getCourse().getCourseName());
        d.setInstructors(instructors);

        return d;
    }

    /**
     * 講座-教員対応マップを生成する
     *
     * @param instructionEntities 講座・教員対応エンティティのリスト
     * @return 講座-教員対応マップ
     */
    private Map<String, List<String>> getInstructionMap(
            List<Instruction> instructionEntities) {
        Map<String, List<String>> instructionMap = new HashMap<>();

        for (Instruction instructionEntity : instructionEntities) {
            String courseId = instructionEntity.getCourseId();
            String instructorName = instructionEntity.getInstructor().getName();

            // 新しいコースIdが来たらkeyにして，valueとして新しいArrayListをnewする
            instructionMap.computeIfAbsent(courseId, k -> new ArrayList<>());

            // ArrayListに教員を追加する
            instructionMap.get(courseId).add(instructorName);
        }

        return instructionMap;
    }
}
