package com.thekey.stylekeyserver.brand.service;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.BRAND_NOT_FOUND;

import com.thekey.stylekeyserver.brand.dto.response.ApiBrandPageResponse;
import com.thekey.stylekeyserver.brand.dto.response.BrandResponse;
import com.thekey.stylekeyserver.brand.entity.Brand;
import com.thekey.stylekeyserver.brand.repository.BrandRepository;
import com.thekey.stylekeyserver.stylepoint.entity.StylePoint;
import com.thekey.stylekeyserver.stylepoint.service.StylePointAdminService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final StylePointAdminService stylePointAdminService;

    @Transactional(readOnly = true)
    public BrandResponse findById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BRAND_NOT_FOUND.getMessage()));
        return BrandResponse.of(brand);
    }

    @Transactional(readOnly = true)
    public ApiBrandPageResponse findAll(Pageable pageable) {
        Slice<Brand> brands = brandRepository.findAll(pageable);
        List<BrandResponse> responses = brands.getContent().stream()
                .map(BrandResponse::of)
                .toList();

        return ApiBrandPageResponse.fromSlice(responses, brands.hasNext());
    }

    @Transactional(readOnly = true)
    public List<BrandResponse> findByStylePointId(Long id) {
        StylePoint stylePoint = stylePointAdminService.findById(id);
        return brandRepository.findBrandByStylePoint(stylePoint).stream()
                .map(BrandResponse::of)
                .toList();
    }

}