package com.thekey.stylekeyserver.coordinatelook.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CoordinateLookResponse {

    @Schema(description = "코디룩 ID")
    private Long id;

    @Schema(description = "코디룩 제목")
    private String title;

    @Schema(description = "코디룩 이미지 URL")
    private String coordinateLookImageUrl;

    @Schema(description = "스타일포인트 ID")
    private Long stylePointId;

    @Builder
    public CoordinateLookResponse(Long id, String title, String coordinateLookImageUrl, Long stylePointId) {
        this.id = id;
        this.title = title;
        this.coordinateLookImageUrl = coordinateLookImageUrl;
        this.stylePointId = stylePointId;
    }

    public static CoordinateLookResponse of(CoordinateLook coordinateLook) {
        String imageUrl = coordinateLook.getImage().getUrl();

        return CoordinateLookResponse.builder()
                .id(coordinateLook.getId())
                .title(coordinateLook.getTitle())
                .coordinateLookImageUrl(imageUrl)
                .stylePointId(coordinateLook.getStylePoint().getId())
                .build();
    }
}
