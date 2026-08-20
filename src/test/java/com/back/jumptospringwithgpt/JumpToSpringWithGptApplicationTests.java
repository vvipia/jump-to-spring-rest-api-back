package com.back.jumptospringwithgpt;

import com.back.jumptospringwithgpt.question.entity.Question;
import com.back.jumptospringwithgpt.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JumpToSpringWithGptApplicationTests {
    @Autowired
    private QuestionService questionService;

    @Test
    void createQuestions() {
        for (int i = 1; i <= 10; i++) {
            questionService.create(
                    "테스트 질문 " + i,
                    "테스트 내용 " + i
            );
        }
    }
}
