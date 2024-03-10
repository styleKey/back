package com.thekey.stylekeyserver.coordinatelook.controller;

import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookDetailsResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookResponse;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CoordinateLook", description = "CoordinateLook API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coordinate-looks")
public class CoordinateLookApiController {

    private final CoordinateLookService coordinateLookService;

    @GetMapping("/{id}")
    @Operation(summary = "Read One CoordinateLook With Items", description = "코디룩 단건 조회. 해당 코디룩 안에 속한 아이템을 함께 반환한다.")
    public ApiResponse<ApiCoordinateLookDetailsResponse> getCoordinateLook(@PathVariable Long id) {
        return ApiResponse.success(coordinateLookService.getCoordinateLookWithLikes(id));
    }

    @GetMapping
    @Operation(summary = "Read All CoordinateLook", description = "코디룩 정보 전체 조회")
    public ApiResponse<List<ApiCoordinateLookResponse>> getCoordinateLooks() {
        return ApiResponse.success(coordinateLookService.getAllCoordinateLooksWithLikes());
    }

    @GetMapping("/style-points/{id}")
    @Operation(summary = "Read All CoordinateLooks By StylePointId", description = "스타일포인트 ID에 해당하는 코디룩 목록 전체 조회")
    public ApiResponse<List<ApiCoordinateLookResponse>> getCoordinateLooksByStylePointId(@PathVariable Long id) {
        return ApiResponse.success(coordinateLookService.getCoordinateLookWithLikesByStylePointId(id));
    }
}
