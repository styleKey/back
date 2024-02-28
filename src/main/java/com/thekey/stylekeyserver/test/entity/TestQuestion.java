package com.thekey.stylekeyserver.test.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test_question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_question_id")
    private Long id;

    @Column(name = "test_question_content")
    private String content;

    @OneToMany(mappedBy = "testQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestAnswer> testAnswers = new ArrayList<>();
}
