package com.thekey.stylekeyserver.stylepoint.controller;

import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import com.thekey.stylekeyserver.stylepoint.entity.StylePoint;
import com.thekey.stylekeyserver.stylepoint.dto.request.StylePointRequest;
import com.thekey.stylekeyserver.stylepoint.dto.response.StylePointResponse;
import com.thekey.stylekeyserver.stylepoint.service.StylePointAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "StylePoint", description = "StylePoint API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/style-points")
public class StylePointAdminController {

    private final StylePointAdminService stylePointAdminService;

    @GetMapping("/{id}")
    @Operation(summary = "Read One StylePoint", description = "스타일포인트 단건 정보 조회")
    public ApiResponse<StylePointResponse> getStylePoint(@PathVariable Long id) {
        Optional<StylePoint> optional = Optional.ofNullable(stylePointAdminService.findById(id));

        return optional.map(stylePoint -> ApiResponse.ok(StylePointResponse.of(stylePoint)))
                .orElse(ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.STYLE_POINT_NOT_FOUND.getMessage()));
    }

    @GetMapping
    @Operation(summary = "Read All StylePoint", description = "스타일포인트 전체 정보 조회")
    public ApiResponse<List<StylePointResponse>> getStylePoints() {
        List<StylePoint> stylePoints = stylePointAdminService.findAll();

        if (stylePoints.isEmpty()) {
            return ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.STYLE_POINT_NOT_FOUND.getMessage());
        }

        return ApiResponse.ok(stylePoints.stream()
                .map(StylePointResponse::of)
                .collect(Collectors.toList()));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update StylePoint", description = "스타일포인트 정보 수정")
    public ApiResponse<StylePointResponse> update(@PathVariable Long id,
                                                  @RequestBody StylePointRequest requestDto) {

        if (id == null) {
            return ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.STYLE_POINT_NOT_FOUND.getMessage());
        }

        StylePoint updated = stylePointAdminService.update(id, requestDto);
        return ApiResponse.ok(StylePointResponse.of(updated));
    }
}
