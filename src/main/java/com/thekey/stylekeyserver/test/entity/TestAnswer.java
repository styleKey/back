package com.thekey.stylekeyserver.test.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "testAnswer", cascade = CascadeType.ALL, orphanRemoval = true)
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