package com.thekey.stylekeyserver.test.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.test.entity.TestResult;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class TestResultResponse {

    List<TestResultDetailResponse> testResultDetails;

    @Builder
    private TestResultResponse(List<TestResultDetailResponse> testResultDetails) {
        this.testResultDetails = testResultDetails;
    }

    public static TestResultResponse of(TestResult testResult) {
        List<TestResultDetailResponse> topTwoDetails = testResult.calculateTopTwoStylePoint().stream()
            .map(TestResultDetailResponse::of)
            .toList();

        return TestResultResponse.builder()
            .testResultDetails(topTwoDetails)
            .build();
    }
}