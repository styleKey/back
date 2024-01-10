package com.thekey.stylekeyserver.brand.controller;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.BrandDto;
import com.thekey.stylekeyserver.brand.service.BrandAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Brand", description = "Brand API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/brands")
public class BrandAdminController {

    private final BrandAdminService brandAdminService;

    @PostMapping
    @Operation(summary = "Create Brand", description = "브랜드 정보 등록")
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandDto requestDto) {
        Optional<Brand> optional = Optional.ofNullable(brandAdminService.create(requestDto));

        return optional.map(createdBrand -> {
            BrandDto response = brandAdminService.convertToDto(createdBrand);
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read One Brand", description = "브랜드 정보 단건 조회")
    public ResponseEntity<BrandDto> getBrand(@PathVariable Long id) {
        Optional<Brand> optional = Optional.ofNullable(brandAdminService.findById(id));

        return optional.map(brand -> {
            BrandDto responseDto = brandAdminService.convertToDto(brand);
            return ResponseEntity.ok(responseDto);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Read All Brands", description = "브랜드 정보 전체 조회")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = brandAdminService.findAll();

        return Optional.of(brands)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).body(brands));
    }

    @GetMapping("style-points/{id}")
    @Operation(summary = "Read All Brands By StylePointId", description = "스타일포인트 ID에 해당하는 브랜드 목록 전체 조회")
    public ResponseEntity<List<Brand>> getBrandsByStylePointId(@PathVariable Long id) {
        List<Brand> brands = brandAdminService.findByStylePointId(id);

        return Optional.of(brands)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).body(brands));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Brand", description = "브랜드 정보 수정")
    public ResponseEntity<BrandDto> updateBrand(@PathVariable Long id,
                                                @RequestBody BrandDto requestDto) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Brand> optional = Optional.ofNullable(brandAdminService.update(id, requestDto));
        return optional.map(brand -> {
            BrandDto response = brandAdminService.convertToDto(brand);
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Brand", description = "브랜드 정보 삭제")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandAdminService.delete(id);
        return ResponseEntity.ok().build();
    }

}
