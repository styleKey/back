package com.thekey.stylekeyserver.test.dto.request;

import com.thekey.stylekeyserver.auth.entity.AuthEntity;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.test.entity.TestResult;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestResultRequest {

    private String userId;
    private Long stylePointId;
    private Integer score;

    @Builder
    private TestResultRequest(String userId, Long stylePointId, Integer score) {
        this.userId = userId;
        this.stylePointId = stylePointId;
        this.score = score;
    }

    @Builder
    public static TestResult toEntity(AuthEntity user, StylePoint stylePoint, TestResultRequest request) {
        return TestResult.builder()
            .user(user)
            .stylePoint(stylePoint)
            .score(request.getScore())
            .build();
    }
}