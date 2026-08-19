package com.back.jumptospringwithgpt.question.dto;
import com.back.jumptospringwithgpt.question.entity.Question;

import java.time.LocalDateTime;

//프론트로 보내는 DTO
public record QuestionResponse(
        Integer id,
        String subject,
        String content,
        LocalDateTime createDate
) {
    public static QuestionResponse from(Question question) {
        return new QuestionResponse(
                question.getId(),
                question.getSubject(),
                question.getContent(),
                question.getCreateDate()
        );
    }
}