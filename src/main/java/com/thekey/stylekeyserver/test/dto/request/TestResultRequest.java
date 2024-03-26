package com.thekey.stylekeyserver.test.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.common.annotation.ExactSize;
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

    private static final String VALIDATE_ANSWER_SIZE = "총 8개의 응답을 보내야합니다. 응답갯수를 확인하세요.";

    @ExactSize(size = 8, message = VALIDATE_ANSWER_SIZE)
    private List<Long> answerIds;

    @Builder
    private TestResultRequest(List<Long> answerIds) {
        this.answerIds = answerIds;
    }
}
