package com.thekey.stylekeyserver.brand.controller;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import com.thekey.stylekeyserver.brand.dto.response.BrandResponse;
import com.thekey.stylekeyserver.brand.service.BrandAdminService;
import com.thekey.stylekeyserver.global.response.ApiResponseDto;
import com.thekey.stylekeyserver.global.response.ErrorType;
import com.thekey.stylekeyserver.global.response.SuccessType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponseDto> createBrand(@RequestPart BrandRequest requestDto,
                                                      @RequestPart("imageFile") MultipartFile imageFile) {

        if (imageFile == null || imageFile.isEmpty()) {
            return ResponseEntity.ok(ApiResponseDto.of(ErrorType.INVALID_IMAGE_FORMAT));
        }

        try {
            Brand brand = brandAdminService.create(requestDto, imageFile);
            BrandResponse response = BrandResponse.of(brand);
            return ResponseEntity.ok(ApiResponseDto.of(SuccessType.SUCCESS, response));
        } catch (FileAlreadyExistsException e) {
            return ResponseEntity.ok(ApiResponseDto.of(ErrorType.FILE_ALREADY_EXISTS));
        } catch (IOException e) {
            return ResponseEntity.ok(ApiResponseDto.of(ErrorType.INVALID_IMAGE_FORMAT));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDto.of(ErrorType.FILE_UPLOAD_FAILED));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read One Brand", description = "브랜드 정보 단건 조회")
    public ResponseEntity<ApiResponseDto> getBrand(@PathVariable Long id) {
        Optional<Brand> optional = Optional.ofNullable(brandAdminService.findById(id));

        return optional.map(brand -> {
            BrandResponse responseDto = BrandResponse.of(brand);
            return ResponseEntity.ok(ApiResponseDto.of(SuccessType.SUCCESS, responseDto));
        }).orElse(ResponseEntity.ok(ApiResponseDto.of(ErrorType.BAD_REQUEST)));
    }

    @GetMapping
    @Operation(summary = "Read All Brands", description = "브랜드 정보 전체 조회")
    public ResponseEntity<List<BrandResponse>> getBrands() {
        List<Brand> brands = brandAdminService.findAll();
        List<BrandResponse> brandDtos = brands.stream()
                .map(BrandResponse::of)
                .collect(Collectors.toList());

        return Optional.of(brandDtos)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).body(brandDtos));
    }

    @GetMapping("style-points/{id}")
    @Operation(summary = "Read All Brands By StylePointId", description = "스타일포인트 ID에 해당하는 브랜드 목록 전체 조회")
    public ResponseEntity<List<BrandResponse>> getBrandsByStylePointId(@PathVariable Long id) {
        List<Brand> brands = brandAdminService.findByStylePointId(id);
        List<BrandResponse> brandDtos = brands.stream()
                .map(BrandResponse::of)
                .collect(Collectors.toList());

        return Optional.of(brandDtos)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).body(brandDtos));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Brand", description = "브랜드 정보 수정")
    public ResponseEntity<ApiResponseDto> updateBrand(@PathVariable Long id,
                                                     @RequestPart BrandRequest requestDto,
                                                     @RequestPart(value = "imageFile", required = false) MultipartFile imageFile)
            throws Exception {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Brand> optional = Optional.ofNullable(brandAdminService.update(id, requestDto, imageFile));
        return optional.map(brand -> {
            BrandResponse response = BrandResponse.of(brand);
            return ResponseEntity.ok(ApiResponseDto.of(SuccessType.SUCCESS, response));
        }).orElse(ResponseEntity.ok(ApiResponseDto.of(ErrorType.BAD_REQUEST)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Brand", description = "브랜드 정보 삭제")
    public ResponseEntity<ApiResponseDto> deleteBrand(@PathVariable Long id) {
        brandAdminService.delete(id);
        return ResponseEntity.ok(ApiResponseDto.of(SuccessType.SUCCESS));
    }

}
