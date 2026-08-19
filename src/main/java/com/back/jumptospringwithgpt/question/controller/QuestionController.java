package com.back.jumptospringwithgpt.question.controller;

import com.back.jumptospringwithgpt.question.dto.QuestionCreateRequest;
import com.back.jumptospringwithgpt.question.dto.QuestionModifyRequest;
import com.back.jumptospringwithgpt.question.dto.QuestionResponse;
import com.back.jumptospringwithgpt.question.entity.Question;
import com.back.jumptospringwithgpt.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    // 전체 질문 반환
    @GetMapping
    public ResponseEntity<List<QuestionResponse>> questions() {
        //Question객체를 하나씩 QuestionResponse로 변환
        List<QuestionResponse> responses = questionService.getQuestionList()
                                                .stream()
                                                .map(QuestionResponse::from)
                                                .toList();
        return ResponseEntity.ok(responses); //상태 코드 200 OK + 데이터 보내기
    }

    // 특정 질문 반환
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> detail(@PathVariable Integer id) {
        QuestionResponse response = QuestionResponse.from(questionService.getQuestion(id));
        return ResponseEntity.ok(response);
    }

    // 질문 생성
    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion
    (@Valid @RequestBody QuestionCreateRequest request) {
        Question question = questionService.create(request.subject(), request.content());
        QuestionResponse response = QuestionResponse.from(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 질문 수정
    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponse> modifyQuestion
    (@PathVariable Integer id, @Valid @RequestBody QuestionModifyRequest request) {
        QuestionResponse response = QuestionResponse.from(questionService.modify(id, request.subject(), request.content()));
        return ResponseEntity.ok(response);
    }

    // 질문 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        questionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
