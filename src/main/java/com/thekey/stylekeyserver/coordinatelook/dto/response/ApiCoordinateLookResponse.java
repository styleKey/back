package com.thekey.stylekeyserver.coordinatelook.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public class ApiCoordinateLookResponse {

    @Schema(description = "코디룩 ID")
    private Long id;

    @Schema(description = "코디룩 제목")
    private String title;

    @Schema(description = "코디룩 이미지 URL")
    private String coordinateLookImageUrl;

    @Schema(description = "스타일포인트 ID")
    private Long stylePointId;

    @Schema(description = "코디룩 좋아요 개수")
    private Integer likeCount;

    @Builder
    public ApiCoordinateLookResponse(Long id, String title, String coordinateLookImageUrl, Long stylePointId,
                                     Integer likeCount) {
        this.id = id;
        this.title = title;
        this.coordinateLookImageUrl = coordinateLookImageUrl;
        this.stylePointId = stylePointId;
        this.likeCount = likeCount;
    }

    public static ApiCoordinateLookResponse of(CoordinateLook coordinateLook, Integer likeCount) {
        String imageUrl = coordinateLook.getImage().getUrl();

        return ApiCoordinateLookResponse.builder()
                .id(coordinateLook.getId())
                .title(coordinateLook.getTitle())
                .coordinateLookImageUrl(imageUrl)
                .stylePointId(coordinateLook.getStylePoint().getId())
                .likeCount(likeCount)
                .build();
    }
}
