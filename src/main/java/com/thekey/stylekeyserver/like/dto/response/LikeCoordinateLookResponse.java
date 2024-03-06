package com.thekey.stylekeyserver.like.dto.response;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeCoordinateLookResponse {

    @Schema(description = "코디룩 ID")
    private Long id;

    @Schema(description = "코디룩 제목")
    private String title;

    @Schema(description = "코디룩 이미지 URL")
    private String coordinateLookImageUrl;

    @Schema(description = "스타일포인트 ID")
    private Long stylePointId;

    @Builder
    public LikeCoordinateLookResponse(Long id, String title, String coordinateLookImageUrl, Long stylePointId) {
        this.id = id;
        this.title = title;
        this.coordinateLookImageUrl = coordinateLookImageUrl;
        this.stylePointId = stylePointId;
    }

    public static LikeCoordinateLookResponse of(CoordinateLook coordinateLook) {
        String imageUrl = coordinateLook.getImage().getUrl();

        return LikeCoordinateLookResponse.builder()
                .id(coordinateLook.getId())
                .title(coordinateLook.getTitle())
                .coordinateLookImageUrl(imageUrl)
                .stylePointId(coordinateLook.getStylePoint().getId())
                .build();
    }
}
