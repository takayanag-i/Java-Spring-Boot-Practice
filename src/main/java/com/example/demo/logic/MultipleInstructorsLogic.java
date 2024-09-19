package com.example.demo.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.example.demo.common.DayOfWeek;
import com.example.demo.dto.CourseDto;
import com.example.demo.model.Instruction;
import com.example.demo.model.Time;

/**
 * 複数の教員で講座を担当するケースに対応するためのロジック
 */
@Service
public class MultipleInstructorsLogic {
    /**
     * 表示用の講座オブジェクトを取得する
     * 
     * @param courseEntity 講座エンティティ（教員情報なし）
     * @return 講座オブジェクト
     * @return null 過剰の講座エンティティに対してはnullを返却する
     */
    public CourseDto merge(Time time, List<Instruction> instructions) {
        Map<String, List<String>> instructionMap = this.getInstructionMap(instructions);
        List<String> instructorNames = instructionMap.get(time.getCourse().getCourseId());

        if (instructorNames == null) {
            // 教員名で検索があった場合などは
            // 講座エンティティの数 > 講座担当エンティティの数なので
            return null;
        }

        CourseDto dto = new CourseDto();
        dto.setDayOfWeek(DayOfWeek.fromNum(time.getDayOfWeek()));
        dto.setPeriod(time.getPeriod());
        dto.setCourseId(time.getCourse().getCourseId());
        dto.setCourseName(time.getCourse().getCourseName());
        dto.setInstructorNames(instructorNames);

        return dto;
    }

    /**
     * 講座-教員対応マップを生成する
     * 
     * @param instructions
     * @return 講座-教員対応マップ（key: 講座ID value: 教員名）
     */
    private Map<String, List<String>> getInstructionMap(
            List<Instruction> instructions) {
        Map<String, List<String>> instructionMap = new HashMap<>();

        for (Instruction instruction : instructions) {
            String courseId = instruction.getCourse().getCourseId();
            String instructorName = instruction.getInstructor().getName();

            // 新しいコースIdが来たらkeyにして，valueとして新しいArrayListをnewする
            instructionMap.computeIfAbsent(courseId, k -> new ArrayList<>());

            // ArrayListに教員を追加する
            instructionMap.get(courseId).add(instructorName);
        }

        return instructionMap;
    }
}
