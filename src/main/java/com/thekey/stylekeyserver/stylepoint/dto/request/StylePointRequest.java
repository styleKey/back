package com.thekey.stylekeyserver.stylepoint.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class StylePointRequest {

    @Schema(description = "스타일포인트 이름", example = "Unique")
    private String title;

    @Schema(description = "스타일포인트 설명", example = "변화하는 트렌드를 반영하여 평범하지 않고 개성있는 디테일을 추구하는 스타일")
    private String description;

    @Schema(description = "스타일포인트 이미지", example = "https://stylekeybucket.s3.ap-northeast-2.amazonaws.com/stylepoint/%E1%84%8B%E1%85%B2%E1%84%82%E1%85%B5%E1%84%8F%E1%85%B3%E1%84%91%E1%85%A9%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%90%E1%85%B3.png")
    private String image;

    @Builder
    public StylePointRequest(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public static StylePointRequest of(StylePoint stylePoint) {
        return StylePointRequest.builder()
                .title(stylePoint.getTitle())
                .description(stylePoint.getDescription())
                .image(stylePoint.getImage())
                .build();
    }
}
