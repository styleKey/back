package com.thekey.stylekeyserver.test.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.test.entity.TestResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class TestResultResponse {

    private Long id;
    private StylePoint stylePoint;
    private Integer score;

    @Builder
    private TestResultResponse(Long id, StylePoint stylePoint, Integer score) {
        this.id = id;
        this.stylePoint = stylePoint;
        this.score = score;
    }

    public static TestResultResponse of(TestResult testResult) {
        return TestResultResponse.builder()
            .id(testResult.getId())
            .stylePoint(testResult.getStylePoint())
            .score(testResult.getScore())
            .build();
    }
}
