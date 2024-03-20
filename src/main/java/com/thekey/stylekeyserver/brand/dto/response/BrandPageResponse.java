package com.thekey.stylekeyserver.brand.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class BrandPageResponse {

    private List<BrandResponse> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    @Builder
    private BrandPageResponse(List<BrandResponse> content, int pageNo, int pageSize, long totalElements, int totalPages,
                             boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public static BrandPageResponse of(List<BrandResponse> content, int pageNo, int pageSize, long totalElements, int totalPages,
                              boolean last) {
        return BrandPageResponse.builder()
                .content(content)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .last(last)
                .build();
    }
}
