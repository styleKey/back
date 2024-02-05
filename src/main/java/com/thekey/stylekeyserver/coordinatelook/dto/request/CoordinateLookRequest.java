package com.thekey.stylekeyserver.coordinatelook.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CoordinateLookRequest {

    @Schema(description = "코디룩 제목", example = "유니크 코디룩1")
    private String title;

    @Schema(description = "코디룩 이미지 URL", example = "coordinate_look_image")
    private String image;

    @Schema(description = "스타일포인트 ID", example = "1")
    private Long stylePointId;

    @Schema(description = "아이템 목록")
    private List<ItemRequest> items;

    @Builder
    public CoordinateLookRequest(String title, String image, Long stylePointId, List<ItemRequest> items) {
        this.title = title;
        this.image = image;
        this.stylePointId = stylePointId;
        this.items = items;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ItemRequest> getItems() {
        return items;
    }

    public CoordinateLook toEntity(StylePoint stylePoint) {
        return CoordinateLook.builder()
                .title(this.title)
                .image(this.image)
                .stylePoint(stylePoint)
                .build();
    }
}
