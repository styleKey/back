package com.thekey.stylekeyserver.like.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class LikeItemRequest {

    @Schema(description = "좋아요한 아이템 ID 리스트")
    private List<Long> itemIds;
}
