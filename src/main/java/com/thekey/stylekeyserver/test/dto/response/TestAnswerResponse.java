package com.thekey.stylekeyserver.test.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.test.entity.TestAnswer;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class TestAnswerResponse {

    private Long answerId;
    private String content;

    @Builder
    private TestAnswerResponse(Long answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }


    public static TestAnswerResponse of(TestAnswer testAnswer) {
        return TestAnswerResponse.builder()
            .answerId(testAnswer.getId())
            .content(testAnswer.getContent())
            .build();
    }
}
