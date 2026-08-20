package com.back.jumptospringwithgpt.answer.repository;


import com.back.jumptospringwithgpt.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByQuestionId(Integer questionId);
}