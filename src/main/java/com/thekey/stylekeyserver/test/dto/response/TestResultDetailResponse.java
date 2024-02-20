package com.thekey.stylekeyserver.test.dto.response;

import com.thekey.stylekeyserver.test.entity.TestResultDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TestResultDetailResponse {

    private String stylePoint;
    private Integer score;

    @Builder
    private TestResultDetailResponse(String stylePoint, Integer score) {
        this.stylePoint = stylePoint;
        this.score = score;
    }

    public static TestResultDetailResponse of(TestResultDetail detail) {
        return TestResultDetailResponse.builder()
            .stylePoint(detail.getStylePoint().getTitle())
            .score(detail.getScore())
            .build();
    }
}
