package com.thekey.stylekeyserver.coordinatelook.controller;

import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookDetailsResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookResponse;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CoordinateLook", description = "CoordinateLook API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coordinate-looks")
public class CoordinateLookApiController {

    private final CoordinateLookAdminService coordinateLookAdminService;
    private final LikeCoordinateLookService likeCoordinateLookService;

    @GetMapping("/{id}")
    @Operation(summary = "Read One CoordinateLook With Items", description = "코디룩 단건 조회. 해당 코디룩 안에 속한 아이템을 함께 반환한다.")
    public ApiResponse<ApiCoordinateLookDetailsResponse> getCoordinateLook(@PathVariable Long id) {
        Optional<CoordinateLook> optional = Optional.ofNullable(coordinateLookAdminService.findById(id));

        return optional.map(coordinateLook -> {
            Integer likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(id);
            ApiCoordinateLookDetailsResponse response = ApiCoordinateLookDetailsResponse.of(coordinateLook, likeCount, coordinateLook.getItems());
            return ApiResponse.success(response);
        }).orElse(ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage()));
    }

    @GetMapping
    @Operation(summary = "Read All CoordinateLook", description = "코디룩 정보 전체 조회")
    public ApiResponse<List<ApiCoordinateLookResponse>> getCoordinateLooks() {
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findAll();
        List<ApiCoordinateLookResponse> response = new ArrayList<>();

        for (CoordinateLook coordinateLook : coordinateLooks) {
            Integer likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(coordinateLook.getId());
            ApiCoordinateLookResponse coordinateLookResponse = ApiCoordinateLookResponse.of(coordinateLook, likeCount);
            response.add(coordinateLookResponse);
        }

        return ApiResponse.success(response);
    }

    @GetMapping("/style-points/{id}")
    @Operation(summary = "Read All CoordinateLooks By StylePointId", description = "스타일포인트 ID에 해당하는 코디룩 목록 전체 조회")
    public ApiResponse<List<ApiCoordinateLookResponse>> getCoordinateLooksByStylePointId(@PathVariable Long id) {
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findByStylePointId(id);
        List<ApiCoordinateLookResponse> response = new ArrayList<>();

        for (CoordinateLook coordinateLook : coordinateLooks) {
            Integer likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(id);
            ApiCoordinateLookResponse coordinateLookResponse = ApiCoordinateLookResponse.of(coordinateLook, likeCount);
            response.add(coordinateLookResponse);
        }

        return ApiResponse.success(response);
    }
}
