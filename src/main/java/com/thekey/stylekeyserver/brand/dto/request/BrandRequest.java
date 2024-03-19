package com.thekey.stylekeyserver.brand.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class BrandRequest {

    @NotNull(message = "브랜드 이름은 필수 입력값입니다.")
    @Schema(description = "브랜드 이름")
    private String title;

    @NotNull(message = "브랜드 영문 이름은 필수 입력값입니다.")
    @Schema(description = "브랜드 영문 이름")
    private String title_eng;

    @NotNull(message = "브랜드 웹 사이트 URL은 필수 입력값입니다.")
    @Schema(description = "브랜드 웹 사이트 URL")
    private String site_url;

    @NotNull(message = "스타일 포인트 ID는 필수 입력값입니다.")
    @Schema(description = "스타일 포인트 ID")
    private Long stylePointId;

    @Builder
    public BrandRequest(String title, String title_eng, String site_url, Long stylePointId) {
        this.title = title;
        this.title_eng = title_eng;
        this.site_url = site_url;
        this.stylePointId = stylePointId;
    }

    public Brand toEntity(StylePoint stylePoint) {
        return Brand.builder()
                .title(this.title)
                .title_eng(this.title_eng)
                .site_url(this.site_url)
                .stylePoint(stylePoint)
                .build();
    }
}