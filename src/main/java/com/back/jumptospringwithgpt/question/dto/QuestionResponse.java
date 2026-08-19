package com.back.jumptospringwithgpt.question.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionCreateRequest {
    @NotEmpty(message = "제목은 필수입니다.")
    @Size(max = 200, message = "제목은 200자 이하로 작성해주세요.")
    private String subject;

    @NotEmpty(message = "내용은 필수입니다.")
    private String content;
}
