package com.thekey.stylekeyserver.coordinatelook.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CoordinateLookRequest {

    @NotNull
    @Schema(description = "코디룩 제목")
    private String title;

    @NotNull
    @Schema(description = "스타일포인트 ID")
    private Long stylePointId;

    @NotNull
    @Schema(description = "아이템 목록")
    private List<ItemRequest> items;

    @Builder
    public CoordinateLookRequest(String title, Long stylePointId, List<ItemRequest> items) {
        this.title = title;
        this.stylePointId = stylePointId;
        this.items = items;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ItemRequest> getItems() {
        return items;
    }

    public CoordinateLook toEntity(StylePoint stylePoint) {
        return CoordinateLook.builder()
                .title(this.title)
                .stylePoint(stylePoint)
                .build();
    }
}
