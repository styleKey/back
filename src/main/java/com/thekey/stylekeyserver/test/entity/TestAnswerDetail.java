package com.thekey.stylekeyserver.test.entity;

import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test_answer_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestAnswerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_answer_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_answer_id")
    private TestAnswer testAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_point_id")
    private StylePoint stylePoint;

    @Column
    private Integer score;

    @Builder
    private TestAnswerDetail(TestAnswer testAnswer, StylePoint stylePoint, Integer score) {
        this.testAnswer = testAnswer;
        this.stylePoint = stylePoint;
        this.score = score;
    }
}
