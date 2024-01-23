package com.thekey.stylekeyserver.test.dto.response;

import com.thekey.stylekeyserver.auth.entity.AuthEntity;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.test.entity.TestResult;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TestResponse {

    private Long id;
    private AuthEntity user;
    private StylePoint stylePoint;
    private Integer score;

    @Builder
    private TestResponse(Long id, AuthEntity user, StylePoint stylePoint, Integer score) {
        this.id = id;
        this.user = user;
        this.stylePoint = stylePoint;
        this.score = score;
    }

    public static TestResponse of(TestResult testResult) {
        return TestResponse.builder()
            .id(testResult.getId())
            .user(testResult.getUser())
            .stylePoint(testResult.getStylePoint())
            .score(testResult.getScore())
            .build();
    }
}
