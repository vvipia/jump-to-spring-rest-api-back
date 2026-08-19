package com.back.jumptospringwithgpt.question.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QuestionResponse {

    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
}
