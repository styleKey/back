package com.thekey.stylekeyserver.coordinatelook.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CoordinateLookResponse {

    @Schema(description = "코디룩 ID", example = "1")
    private Long id;

    @Schema(description = "코디룩 제목", example = "유니크 코디룩1")
    private String title;

    @Schema(description = "코디룩 이미지 URL", example = "coordinate_look_image")
    private String image;

    @Schema(description = "스타일포인트 ID", example = "1")
    private Long stylePointId;

    @Builder
    public CoordinateLookResponse(Long id, String title, String image, Long stylePointId) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.stylePointId = stylePointId;
    }

    public static CoordinateLookResponse of(CoordinateLook coordinateLook) {
        return CoordinateLookResponse.builder()
                .id(coordinateLook.getId())
                .title(coordinateLook.getTitle())
                .image(coordinateLook.getImage())
                .stylePointId(coordinateLook.getStylePoint().getId())
                .build();
    }
}
