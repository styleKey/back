package com.thekey.stylekeyserver.test.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.test.entity.TestQuestion;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class TestQuestionResponse {

    private Long questionId;
    private String content;
    private List<TestAnswerResponse> answers;

    @Builder
    private TestQuestionResponse(Long questionId, String content, List<TestAnswerResponse> answers) {
        this.questionId = questionId;
        this.content = content;
        this.answers = answers;
    }

    public static TestQuestionResponse of(TestQuestion testQuestion) {
        return TestQuestionResponse.builder()
            .questionId(testQuestion.getId())
            .content(testQuestion.getContent())
            .answers(testQuestion.getTestAnswers().stream()
                .map(TestAnswerResponse::of)
                .toList())
            .build();
    }
}
