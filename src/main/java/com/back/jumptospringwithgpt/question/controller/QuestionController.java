package com.back.jumptospringwithgpt.question.controller;

import com.back.jumptospringwithgpt.question.dto.QuestionCreateRequest;
import com.back.jumptospringwithgpt.question.dto.QuestionModifyRequest;
import com.back.jumptospringwithgpt.question.dto.QuestionResponse;
import com.back.jumptospringwithgpt.question.entity.Question;
import com.back.jumptospringwithgpt.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    // 전체 질문 반환
    @GetMapping
    public List<QuestionResponse> questions() {
        return questionService.getQuestionList()
                .stream()
                .map(QuestionResponse::from) //Question객체를 하나씩 QuestionResponse로 변환
                .toList();
    }

    // 특정 질문 반환
    @GetMapping("/{id}")
    public QuestionResponse detail(@PathVariable Integer id) {
        return QuestionResponse.from(questionService.getQuestion(id));
    }

    // 질문 생성
    @PostMapping
    public QuestionResponse createQuestion(@RequestBody QuestionCreateRequest request) {
        Question question = questionService.create(request.subject(), request.content());
        return QuestionResponse.from(question);
    }

    // 질문 수정
    @PatchMapping("/{id}")
    public QuestionResponse modifyQuestion(@PathVariable Integer id,
                                   @RequestBody QuestionModifyRequest request) {
        return QuestionResponse.from(questionService.modify(id, request.subject(), request.content()));
    }

    // 질문 삭제
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.delete(id);
    }
}
