package com.thekey.stylekeyserver.coordinatelook.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiCoordinateLookPageResponse {
    private final List<ApiCoordinateLookResponse> content;
    private final boolean hasNext;

    @Builder
    private ApiCoordinateLookPageResponse(List<ApiCoordinateLookResponse> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }

    public static ApiCoordinateLookPageResponse fromSlice(List<ApiCoordinateLookResponse> content, boolean hasNext) {
        return ApiCoordinateLookPageResponse.builder()
                .content(content)
                .hasNext(hasNext)
                .build();
    }
}
