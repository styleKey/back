package com.thekey.stylekeyserver.brand.service;

import com.thekey.stylekeyserver.brand.BrandErrorMessage;
import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import com.thekey.stylekeyserver.brand.repository.BrandRepository;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.stylepoint.service.StylePointAdminService;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandAdminServiceImpl implements BrandAdminService {

    private final BrandRepository brandRepository;
    private final StylePointAdminService stylePointAdminService;

    @Override
    public Brand create(BrandRequest requestDto) {
        StylePoint stylePoint = stylePointAdminService.findById(requestDto.getStylePointId());
        return brandRepository.save(requestDto.toEntity(stylePoint));
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BrandErrorMessage.NOT_FOUND_BRAND.get() + id));
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> findByStylePointId(Long id) {
        StylePoint stylePoint = stylePointAdminService.findById(id);
        return brandRepository.findBrandByStylePoint(stylePoint);
    }

    @Override
    public Brand update(Long id, BrandRequest requestDto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BrandErrorMessage.NOT_FOUND_BRAND.get() + id));

        StylePoint stylePoint = stylePointAdminService.findById(requestDto.getStylePointId());

        brand.update(requestDto.getTitle(),
                requestDto.getTitle_eng(),
                requestDto.getDescription(),
                requestDto.getSite_url(),
                requestDto.getImage(),
                requestDto.toEntity(stylePoint).getStylePoint());

        return brand;
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

}
