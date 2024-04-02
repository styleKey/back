package com.thekey.stylekeyserver.coordinatelook.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CoordinateLookPageResponse {

    private List<CoordinateLookResponse> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    @Builder
    private CoordinateLookPageResponse(List<CoordinateLookResponse> content, int pageNo, int pageSize,
                                       long totalElements,
                                       int totalPages, boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public static CoordinateLookPageResponse of(List<CoordinateLookResponse> content, Page<CoordinateLook> coordinateLookPage) {
        return CoordinateLookPageResponse.builder()
                .content(content)
                .pageNo(coordinateLookPage.getNumber())
                .pageSize(coordinateLookPage.getSize())
                .totalElements(coordinateLookPage.getTotalElements())
                .totalPages(coordinateLookPage.getTotalPages())
                .last(coordinateLookPage.isLast())
                .build();
    }
}
