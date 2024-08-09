package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Instruction;
import com.example.demo.model.InstructionId;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, InstructionId>,
        JpaSpecificationExecutor<Instruction> {
    List<Instruction> findByCourseIdIn(List<String> courseIds);

    List<Instruction> findByInstructorNameContaining(String instructorName);
}
