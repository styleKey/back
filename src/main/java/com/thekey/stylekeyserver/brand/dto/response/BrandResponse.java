package com.thekey.stylekeyserver.brand.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.brand.domain.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class BrandResponse {

    @Schema(description = "브랜드 ID", example = "1")
    private Long id;

    @Schema(description = "브랜드 이름", example = "솔티페블")
    private String title;

    @Schema(description = "브랜드 영문 이름", example = "salty pebble")
    private String title_eng;

    @Schema(description = "브랜드 설명", example = "솔티페블은 일정한 형식이나 틀을 갖추지 않은 조약돌의 본질을 바라보고 비정형화적인 이미지에 맞춰 시대를 유동하는 컨템포러리 브랜드이다.")
    private String description;

    @Schema(description = "브랜드 웹 사이트 URL", example = "https://www.saltypebble.com/")
    private String site_url;

    @Schema(description = "브랜드 이미지 URL", example = "brand_image_url")
    private String image;

    @Schema(description = "스타일 포인트 ID", example = "1")
    private Long stylePointId;

    @Builder
    public BrandResponse(Long id, String title, String title_eng, String description, String site_url, String image,
                         Long stylePointId) {
        this.id = id;
        this.title = title;
        this.title_eng = title_eng;
        this.description = description;
        this.site_url = site_url;
        this.image = image;
        this.stylePointId = stylePointId;
    }

    public static BrandResponse of(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .title(brand.getTitle())
                .title_eng(brand.getTitle_eng())
                .description(brand.getDescription())
                .site_url(brand.getSite_url())
                .image(brand.getImage())
                .stylePointId(brand.getStylePoint().getId())
                .build();
    }
}
