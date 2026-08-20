package com.back.jumptospringwithgpt.answer.service;

import com.back.jumptospringwithgpt.answer.entity.Answer;
import com.back.jumptospringwithgpt.answer.repository.AnswerRepository;
import com.back.jumptospringwithgpt.global.DataNotFoundException;
import com.back.jumptospringwithgpt.question.entity.Question;
import com.back.jumptospringwithgpt.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    // 특정 질문의 답변 목록
    public List<Answer> getList(Integer questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
    // 답변 하나 조회
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("답변을 찾을 수 없습니다.");
        }
    }
    // 답변 작성
    @Transactional
    public Answer create(Integer questionId, String content) {
        Question question = questionService.getQuestion(questionId);
        Answer answer = new Answer(question, content);
        return answerRepository.save(answer);
    }
    // 답변 수정
    @Transactional
    public Answer modify(Integer answerId, String content) {
        Answer answer = getAnswer(answerId);
        answer.modify(content);
        return answer;
    }
    // 답변 삭제
    @Transactional
    public void delete(Integer answerId) {
        Answer answer = getAnswer(answerId);
        answerRepository.delete(answer);
    }
}