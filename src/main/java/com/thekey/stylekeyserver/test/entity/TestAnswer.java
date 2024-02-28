package com.thekey.stylekeyserver.test.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "test_answer")
public class TestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_answer_id")
    private Long id;

    @Column(name = "test_answer_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_question_id")
    private TestQuestion testQuestion;

    @OneToMany(mappedBy = "testAnswer", cascade = CascadeType.ALL)
    private List<TestAnswerDetail> testAnswerDetails = new ArrayList<>();

    // 연관관계 편의 메서드 //
    public void relate(TestQuestion testQuestion) {
        if (this.testQuestion != null) {
            this.testQuestion.getTestAnswers().remove(this);
        }
        this.testQuestion = testQuestion;
        testQuestion.getTestAnswers().add(this);
    }
}