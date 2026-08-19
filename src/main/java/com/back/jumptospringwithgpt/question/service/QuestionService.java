package com.back.jumptospringwithgpt.question.service;

import com.back.jumptospringwithgpt.global.DataNotFoundException;
import com.back.jumptospringwithgpt.question.entity.Question;
import com.back.jumptospringwithgpt.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    // 질문 목록 조회
    public List<Question> getQuestionList() {
        return this.questionRepository.findAll();
    }

    // 질문 하나 조회
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        }
        else {
            throw new DataNotFoundException("질문을 찾을 수 없습니다.");
        }
    }

    // 질문 등록
    @Transactional
    public Question create(String subject, String content) {

        Question question = new Question();

        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());

        return questionRepository.save(question);
    }

    // 질문 수정
    @Transactional // 여기에 save가 없는 이유 -> JPA가 값 수정을 감지 후 더티 체킹으로 업데이트 발생
    public Question modify(Integer id, String subject, String content) {
        Question question = getQuestion(id);

        question.setSubject(subject);
        question.setContent(content);

        return question;
    }


    // 질문 삭제
    @Transactional
    public void delete(Integer id) {
        Question question = getQuestion(id);

        questionRepository.delete(question);
    }
}

