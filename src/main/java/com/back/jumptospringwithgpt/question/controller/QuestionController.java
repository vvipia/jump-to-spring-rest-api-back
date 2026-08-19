package com.back.jumptospringwithgpt.question.controller;

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
    @GetMapping()
    public List<Question> questions() {
        return this.questionService.getQuestionList();
    }

    // 특정 질문 반환
    @GetMapping("/{id}")
    public Question detail(@PathVariable Integer id) {
        return questionService.getQuestion(id);
    }

    // 질문 생성
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.create(question.getSubject(), question.getContent());
    }

    // 질문 수정
    @PatchMapping("/{id}")
    public Question modifyQuestion(@PathVariable Integer id,
                                   @RequestBody Question question) {
        return questionService.modify(id, question.getSubject(), question.getContent());
    }

    // 질문 삭제
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.delete(id);
    }
}
