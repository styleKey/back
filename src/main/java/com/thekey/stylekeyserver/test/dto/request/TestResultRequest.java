package com.thekey.stylekeyserver.test.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class TestResultRequest {

    private List<Long> answerIds;

    @Builder
    private TestResultRequest(List<Long> answerIds) {
        this.answerIds = answerIds;
    }
}