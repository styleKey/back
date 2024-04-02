package com.thekey.stylekeyserver.brand.service;

import com.thekey.stylekeyserver.brand.entity.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import com.thekey.stylekeyserver.brand.dto.response.BrandPageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface BrandAdminService {

    Brand create(BrandRequest requestDto, MultipartFile brandImageFile);

    Brand findById(Long id);

    List<Brand> findAll();

    BrandPageResponse findAllPaging(Pageable pageable);

    List<Brand> findByStylePointId(Long id);

    Brand update(Long id, BrandRequest requestDto, MultipartFile brandImageFile);

    void delete(Long id);

}