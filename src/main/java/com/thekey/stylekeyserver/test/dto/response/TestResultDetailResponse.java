package com.thekey.stylekeyserver.test.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.test.entity.TestResultDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class TestResultDetailResponse {

    private Long stylePointId;
    private String stylePointImage;
    private Integer score;

    @Builder
    private TestResultDetailResponse(StylePoint stylePoint, Integer score) {
        this.stylePointId = stylePoint.getId();
        this.stylePointImage = stylePoint.getImage();
        this.score = score;
    }

    public static TestResultDetailResponse of(TestResultDetail detail) {
        return TestResultDetailResponse.builder()
            .stylePoint(detail.getStylePoint())
            .score(detail.getScore())
            .build();
    }
}
