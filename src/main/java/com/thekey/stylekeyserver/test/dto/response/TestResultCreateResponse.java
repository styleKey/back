package com.thekey.stylekeyserver.test.dto.response;

import lombok.Builder;

public class TestResultCreateResponse {

    private Long testResultId;

    @Builder
    private TestResultCreateResponse(Long testResultId) {
        this.testResultId = testResultId;
    }

    public static TestResultCreateResponse of(Long testResultId) {
        return TestResultCreateResponse.builder()
            .testResultId(testResultId)
            .build();
    }
}
