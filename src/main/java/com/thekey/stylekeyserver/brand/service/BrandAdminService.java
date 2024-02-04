package com.thekey.stylekeyserver.brand.service;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import java.util.List;

public interface BrandAdminService {

    Brand create(BrandRequest requestDto);

    Brand findById(Long id);

    List<Brand> findAll();

    List<Brand> findByStylePointId(Long id);

    Brand update(Long id, BrandRequest requestDto);

    void delete(Long id);

}
