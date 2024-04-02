package com.thekey.stylekeyserver.coordinatelook.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import com.thekey.stylekeyserver.item.entity.Item;
import com.thekey.stylekeyserver.item.dto.response.ItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CoordinateLookDetailsResponse {

    @Schema(description = "코디룩 ID", example = "1")
    private Long id;

    @Schema(description = "코디룩 제목", example = "유니크 코디룩1")
    private String title;

    @Schema(description = "코디룩 이미지 URL", example = "coordinate_look_image")
    private String imageUrl;

    @Schema(description = "스타일포인트 ID", example = "1")
    private Long stylePointId;

    @Schema(description = "아이템 목록")
    private List<ItemResponse> items;

    @Builder
    public CoordinateLookDetailsResponse(Long id, String title, String imageUrl, Long stylePointId, List<ItemResponse> items) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.stylePointId = stylePointId;
        this.items = items;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ItemResponse> getItems() {
        return items;
    }

    public static CoordinateLookDetailsResponse of(CoordinateLook coordinateLook, List<Item> items) {
        List<ItemResponse> itemResponses = items.stream()
                .map(ItemResponse::of)
                .collect(Collectors.toList());

        String imageUrl = coordinateLook.getImage().getUrl();

        return CoordinateLookDetailsResponse.builder()
                .id(coordinateLook.getId())
                .title(coordinateLook.getTitle())
                .imageUrl(imageUrl)
                .stylePointId(coordinateLook.getStylePoint().getId())
                .items(itemResponses)
                .build();
    }
}

