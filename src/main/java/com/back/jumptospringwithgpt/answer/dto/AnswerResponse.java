package com.back.jumptospringwithgpt.answer.dto;

import com.back.jumptospringwithgpt.answer.entity.Answer;

import java.time.LocalDateTime;

public record AnswerResponse(Integer id,
                             String content,
                             LocalDateTime createDate) {
    public static AnswerResponse from(Answer answer) {
        return new AnswerResponse(answer.getId(),
                                  answer.getContent(),
                                  answer.getCreateDate());
    }
}
