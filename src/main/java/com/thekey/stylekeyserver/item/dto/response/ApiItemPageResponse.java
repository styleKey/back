package com.thekey.stylekeyserver.item.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiItemPageResponse {

    private final List<ApiItemResponse> content;
    private final boolean hasNext;

    @Builder
    private ApiItemPageResponse(List<ApiItemResponse> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }

    public static ApiItemPageResponse fromSlice(List<ApiItemResponse> content, boolean hasNext) {
        return ApiItemPageResponse.builder()
                .content(content)
                .hasNext(hasNext)
                .build();
    }
}