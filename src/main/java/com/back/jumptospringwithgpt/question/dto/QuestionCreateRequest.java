package com.back.jumptospringwithgpt.question.dto;

//record는 게터 필요 없이 데이터를 담는 용도
public record QuestionCreateRequest(String subject, String content) {
}
