package com.thekey.stylekeyserver.coordinatelook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.item.dto.ItemDto;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoordinateLookDto {

    @Hidden
    private Long id;

    @Schema(description = "코디룩 제목", example = "유니크 코디룩1")
    private String title;

    @Schema(description = "코디룩 이미지 URL", example = "coordinate_look_image")
    private String image;

    @Schema(description = "스타일포인트 ID", example = "1")
    private Long stylePointId;

    @Schema(description = "아이템 목록")
    private List<ItemDto> items;

    @Builder
    public CoordinateLookDto(Long id, String title, String image, Long stylePointId, List<ItemDto> items) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.stylePointId = stylePointId;
        this.items = items;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ItemDto> getItems() {
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
