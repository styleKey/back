package com.thekey.stylekeyserver.brand.service;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.BrandDto;
import java.util.List;

public interface BrandAdminService {

    Brand create(BrandDto requestDto);

    Brand findById(Long id);

    List<Brand> findAll();

    Brand update(Long id, BrandDto requestDto);

    void delete(Long id);

    BrandDto convertToDto(Brand brand);
}
