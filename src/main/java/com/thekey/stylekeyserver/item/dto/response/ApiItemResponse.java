package com.thekey.stylekeyserver.item.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public class ApiItemResponse {

    @Schema(description = "아이템 ID")
    private Long id;

    @Schema(description = "아이템 이름")
    private String title;

    @Schema(description = "아이템 판매 링크")
    private String sales_link;

    @Schema(description = "아이템 이미지")
    private String itemImageUrl;

    @Schema(description = "브랜드 ID")
    private Long brandId;

    @Schema(description = "카테고리 ID")
    private Long categoryId;

    @Schema(description = "코디룩 ID")
    private Long coordinateLookId;
    
    @Schema(description = "아이템 좋아요 개수")
    private Integer likeCount;

    @Builder
    public ApiItemResponse(Long id, String title, String sales_link, String itemImageUrl, Long brandId, Long categoryId,
                           Long coordinateLookId, Integer likeCount) {
        this.id = id;
        this.title = title;
        this.sales_link = sales_link;
        this.itemImageUrl = itemImageUrl;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.coordinateLookId = coordinateLookId;
        this.likeCount = likeCount;
    }
    
    public static ApiItemResponse of(Item item, Integer likeCount) {
        String imageUrl = item.getImage().getUrl();
        
        return ApiItemResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .sales_link(item.getSales_link())
                .itemImageUrl(imageUrl)
                .brandId(item.getBrand().getId())
                .categoryId(item.getCategory().getId())
                .coordinateLookId(item.getCoordinateLook().getId())
                .likeCount(likeCount)
                .build();
    }
}
