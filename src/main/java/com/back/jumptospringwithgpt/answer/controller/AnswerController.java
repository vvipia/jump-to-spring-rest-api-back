package com.back.jumptospringwithgpt.answer.controller;

import com.back.jumptospringwithgpt.answer.dto.AnswerCreateRequest;
import com.back.jumptospringwithgpt.answer.dto.AnswerModifyRequest;
import com.back.jumptospringwithgpt.answer.dto.AnswerResponse;
import com.back.jumptospringwithgpt.answer.entity.Answer;
import com.back.jumptospringwithgpt.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class AnswerController {
    public final AnswerService answerService;

    // 특정 질문 답변 목록 조회
    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<AnswerResponse>> getAnswers(@PathVariable Integer questionId) {
        List<AnswerResponse> responses = answerService.getList(questionId)
                                                      .stream()
                                                      .map(AnswerResponse::from)
                                                      .toList();
        return ResponseEntity.ok(responses);
    }
    //답변 작성
    @PostMapping("/{questionId}/answers")
    public ResponseEntity<AnswerResponse> createAnswer(@PathVariable Integer questionId,
                                                       @RequestBody AnswerCreateRequest request) {
        Answer answer = answerService.create(questionId, request.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(AnswerResponse.from(answer));
    }
    // 답변 수정a
    @PatchMapping("/api/v1/answers/{answerId}")
    public ResponseEntity<AnswerResponse> modifyAnswer(@PathVariable Integer answerId,
                                                       @RequestBody AnswerModifyRequest request) {
        Answer answer = answerService.modify(answerId, request.content());
        return ResponseEntity.ok(AnswerResponse.from(answer));
    }
    // 답변 삭제
    @DeleteMapping("/api/v1/answers/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Integer answerId) {
        answerService.delete(answerId);
        return ResponseEntity.noContent().build();
    }
}
