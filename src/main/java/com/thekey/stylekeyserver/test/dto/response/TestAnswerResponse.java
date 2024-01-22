package com.thekey.stylekeyserver.test.dto.response;

import com.thekey.stylekeyserver.test.entity.TestAnswer;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TestAnswerResponse {

    private Long id;
    private String content;
    private String image;
    private Long stylePointId;

    @Builder
    private TestAnswerResponse(Long id, String content, String image, Long stylePointId) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.stylePointId = stylePointId;
    }

    public static TestAnswerResponse of(TestAnswer testAnswer) {
        return TestAnswerResponse.builder()
            .id(testAnswer.getId())
            .content(testAnswer.getContent())
            .image(testAnswer.getImage())
            .stylePointId(testAnswer.getStylePoint().getId())
            .build();
    }
}
