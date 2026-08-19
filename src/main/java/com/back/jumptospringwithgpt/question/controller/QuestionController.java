package com.back.jumptospringwithgpt.question.controller;

import com.back.jumptospringwithgpt.question.entity.Question;
import com.back.jumptospringwithgpt.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {

    private final QuestionRepository questionRepository;

    @GetMapping("/questions")
    public List<Question> questions() {
        return this.questionRepository.findAll();
    }
}
