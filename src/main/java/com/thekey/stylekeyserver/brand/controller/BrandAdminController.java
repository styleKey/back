package com.thekey.stylekeyserver.brand.controller;

import com.thekey.stylekeyserver.brand.entity.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import com.thekey.stylekeyserver.brand.dto.response.BrandPageResponse;
import com.thekey.stylekeyserver.brand.dto.response.BrandResponse;
import com.thekey.stylekeyserver.brand.service.BrandAdminService;
import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Brand", description = "Brand API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/brands")
public class BrandAdminController {

    private final BrandAdminService brandAdminService;

    @PostMapping
    @Operation(summary = "Create Brand", description = "브랜드 정보 등록")
    public ApiResponse<Void> createBrand(@RequestPart(required = false) @Valid BrandRequest requestDto,
                                         @RequestPart(value = "brand_imageFile", required = false) MultipartFile brandImageFile) {
        brandAdminService.create(requestDto, brandImageFile);
        return ApiResponse.ok();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read One Brand", description = "브랜드 정보 단건 조회")
    public ApiResponse<BrandResponse> getBrand(@PathVariable Long id) {
        Optional<Brand> optional = Optional.ofNullable(brandAdminService.findById(id));

        return optional.map(brand -> {
            BrandResponse response = BrandResponse.of(brand);
            return ApiResponse.ok(response);
        }).orElse(ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.ERROR_BAD_REQUEST.getMessage()));
    }

    @GetMapping
    @Operation(summary = "Read All Brands", description = "브랜드 정보 전체 조회")
    public ApiResponse<BrandPageResponse> getBrands(
            @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
        return ApiResponse.ok(brandAdminService.findAllPaging(pageable));
    }

    @GetMapping("style-points/{id}")
    @Operation(summary = "Read All Brands By StylePointId", description = "스타일포인트 ID에 해당하는 브랜드 목록 전체 조회")
    public ApiResponse<List<BrandResponse>> getBrandsByStylePointId(@PathVariable Long id) {
        List<Brand> brands = brandAdminService.findByStylePointId(id);
        List<BrandResponse> response = brands.stream()
                .map(BrandResponse::of)
                .collect(Collectors.toList());

        return ApiResponse.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Brand", description = "브랜드 정보 수정")
    public ApiResponse<Void> updateBrand(@PathVariable Long id,
                                         @RequestPart(required = false) BrandRequest requestDto,
                                         @RequestPart(value = "brand_imageFile", required = false) MultipartFile brandImageFile) {
        brandAdminService.update(id, requestDto, brandImageFile);
        return ApiResponse.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Brand", description = "브랜드 정보 삭제")
    public ApiResponse<Void> deleteBrand(@PathVariable Long id) {
        brandAdminService.delete(id);
        return ApiResponse.ok();
    }

}
