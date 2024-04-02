package com.thekey.stylekeyserver.item.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.brand.entity.Brand;
import com.thekey.stylekeyserver.category.entity.Category;
import com.thekey.stylekeyserver.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ItemRequest {

    @Schema(description = "아이템 ID")
    private Long id;

    @Schema(description = "아이템 이름")
    private String title;

    @Schema(description = "아이템 판매 링크")
    private String sales_link;

    @Schema(description = "브랜드 ID")
    private Long brandId;

    @Schema(description = "카테고리 ID")
    private Long categoryId;

    @Builder
    public ItemRequest(Long id, String title, String sales_link, Long brandId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.sales_link = sales_link;
        this.brandId = brandId;
        this.categoryId = categoryId;
    }

    public Item toEntity(Brand brand, Category category) {
        return Item.builder()
                .title(this.title)
                .sales_link(this.sales_link)
                .brand(brand)
                .category(category)
                .build();
    }
}

