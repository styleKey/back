package com.thekey.stylekeyserver.test.dto.response;

import com.thekey.stylekeyserver.test.entity.TestQuestion;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TestQuestionResponse {

    private Long id;
    private String content;
    private List<TestAnswerResponse> answers;

    @Builder
    private TestQuestionResponse(Long id, String content, List<TestAnswerResponse> answers) {
        this.id = id;
        this.content = content;
        this.answers = answers;
    }

    public static TestQuestionResponse of(TestQuestion testQuestion) {
        return TestQuestionResponse.builder()
            .id(testQuestion.getId())
            .content(testQuestion.getContent())
            .answers(testQuestion.getAnswers().stream()
                .map(TestAnswerResponse::of)
                .toList())
            .build();
    }
}
