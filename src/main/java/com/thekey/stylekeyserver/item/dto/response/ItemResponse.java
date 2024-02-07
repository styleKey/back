package com.thekey.stylekeyserver.item.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.item.domain.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ItemResponse {

    @Schema(description = "아이템 ID", example = "1")
    private Long id;

    @Schema(description = "아이템 이름", example = "유니크 아이템1")
    private String title;

    @Schema(description = "아이템 판매 링크", example = "http://m.odlyworkshop.com/product/good-grief-mesh-tee/374/category/72/display/1/")
    private String sales_link;

    @Schema(description = "아이템 이미지", example = "item_image")
    private String image;

    @Schema(description = "브랜드 ID", example = "3")
    private Long brandId;

    @Schema(description = "카테고리 ID", example = "1")
    private Long categoryId;

    @Schema(description = "코디룩 ID", example = "1")
    private Long coordinateLookId;

    @Builder
    public ItemResponse(Long id, String title, String sales_link, String image, Long brandId, Long categoryId, Long coordinateLookId) {
        this.id = id;
        this.title = title;
        this.sales_link = sales_link;
        this.image = image;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.coordinateLookId = coordinateLookId;
    }

    /*TODO: 아이템 단건 조회 시 해당 코디룩 ID도 같이 넣으면 좋을거같음*/
    public static ItemResponse of(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .sales_link(item.getSales_link())
                .image(item.getImage())
                .brandId(item.getBrand().getId())
                .categoryId(item.getCategory().getId())
                .coordinateLookId(item.getCoordinateLook().getId())
                .build();
    }
}
