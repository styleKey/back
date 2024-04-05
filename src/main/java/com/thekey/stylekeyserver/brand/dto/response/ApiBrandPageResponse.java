package com.thekey.stylekeyserver.brand.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiBrandPageResponse {
    private final List<BrandResponse> content;
    private final boolean hasNext;

    @Builder
    private ApiBrandPageResponse(List<BrandResponse> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }

    public static ApiBrandPageResponse fromSlice(List<BrandResponse> content, boolean hasNext) {
        return ApiBrandPageResponse.builder()
                .content(content)
                .hasNext(hasNext)
                .build();
    }
}