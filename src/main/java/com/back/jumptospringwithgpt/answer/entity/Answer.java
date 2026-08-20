package com.back.jumptospringwithgpt.answer.entity;

import com.back.jumptospringwithgpt.question.entity.Question;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private Question question;

    protected Answer() {}

    public Answer(Question question, String content) {
        this.question = question;
        this.content = content;
        this.createDate = LocalDateTime.now();
    }

    public void modify(String content) {
        this.content = content;
        this.modifyDate = LocalDateTime.now();
    }

}
