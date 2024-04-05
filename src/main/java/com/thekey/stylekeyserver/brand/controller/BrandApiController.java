package com.thekey.stylekeyserver.brand.controller;

import com.thekey.stylekeyserver.brand.dto.response.ApiBrandPageResponse;
import com.thekey.stylekeyserver.brand.dto.response.BrandResponse;
import com.thekey.stylekeyserver.brand.service.BrandService;
import com.thekey.stylekeyserver.common.exception.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Brand", description = "Brand API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandApiController {

    private final BrandService brandService;

    @GetMapping("/{id}")
    @Operation(summary = "Read One Brand", description = "브랜드 정보 단건 조회")
    public ApiResponse<BrandResponse> getBrand(@PathVariable Long id) {
        return ApiResponse.ok(brandService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Read All Brands", description = "브랜드 정보 전체 조회. 무한 스크롤")
    public ApiResponse<ApiBrandPageResponse> getBrands(Pageable pageable) {
        return ApiResponse.ok(brandService.findAll(pageable));
    }

    @GetMapping("style-points/{id}")
    @Operation(summary = "Read All Brands By StylePointId", description = "스타일포인트 ID에 해당하는 브랜드 목록 전체 조회")
    public ApiResponse<List<BrandResponse>> getBrandsByStylePointId(@PathVariable Long id) {
        return ApiResponse.ok(brandService.findByStylePointId(id));
    }
}
