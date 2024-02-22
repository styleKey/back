package com.thekey.stylekeyserver.test.entity;


import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "test_result_datail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestResultDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_result_id")
    private TestResult testResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_point_id")
    private StylePoint stylePoint;

    @Column
    private Integer score;

    @Builder
    private TestResultDetail(TestResult testResult, StylePoint stylePoint, Integer score) {
        this.testResult = testResult;
        this.stylePoint = stylePoint;
        this.score = score;
    }

    public static TestResultDetail of(TestResult testResult, StylePoint stylePoint, Integer score) {
        return TestResultDetail.builder()
                .testResult(testResult)
                .stylePoint(stylePoint)
                .score(score)
                .build();
    }
}
