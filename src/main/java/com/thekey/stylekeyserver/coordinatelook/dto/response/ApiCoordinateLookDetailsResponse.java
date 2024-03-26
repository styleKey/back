package com.thekey.stylekeyserver.coordinatelook.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.response.ItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public class ApiCoordinateLookDetailsResponse {

    @Schema(description = "코디룩 ID", example = "1")
    private Long id;

    @Schema(description = "코디룩 제목", example = "유니크 코디룩1")
    private String title;

    @Schema(description = "코디룩 이미지 URL", example = "coordinate_look_image")
    private String imageUrl;

    @Schema(description = "스타일포인트 ID", example = "1")
    private Long stylePointId;

    @Schema(description = "코디룩 좋아요 개수")
    private Integer likeCount;

    @Schema(description = "아이템 목록")
    private List<ItemResponse> items;

    @Builder
    public ApiCoordinateLookDetailsResponse(Long id, String title, String imageUrl, Long stylePointId,
                                            Integer likeCount,
                                            List<ItemResponse> items) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.stylePointId = stylePointId;
        this.likeCount = likeCount;
        this.items = items;
    }

    public static ApiCoordinateLookDetailsResponse of(CoordinateLook coordinateLook, Integer likeCount, List<Item> items) {
        List<ItemResponse> itemResponses = items.stream()
                .map(ItemResponse::of)
                .collect(Collectors.toList());

        String imageUrl = coordinateLook.getImage().getUrl();

        return ApiCoordinateLookDetailsResponse.builder()
                .id(coordinateLook.getId())
                .title(coordinateLook.getTitle())
                .imageUrl(imageUrl)
                .stylePointId(coordinateLook.getStylePoint().getId())
                .likeCount(likeCount)
                .items(itemResponses)
                .build();
    }


}
