package com.thekey.stylekeyserver.test.dto.response;

import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.test.entity.TestResult;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TestResponse {

    private Long id;
    private String userId;
    private StylePoint stylePoint;
    private Integer score;

    @Builder
    private TestResponse(Long id, String userId, StylePoint stylePoint, Integer score) {
        this.id = id;
        this.userId = userId;
        this.stylePoint = stylePoint;
        this.score = score;
    }

    public static TestResponse of(TestResult testResult) {
        return TestResponse.builder()
            .id(testResult.getId())
            .userId(testResult.getUser().getUserId())
            .stylePoint(testResult.getStylePoint())
            .score(testResult.getScore())
            .build();
    }
}
