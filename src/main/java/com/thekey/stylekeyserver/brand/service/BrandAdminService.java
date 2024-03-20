package com.thekey.stylekeyserver.brand.service;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import com.thekey.stylekeyserver.brand.dto.response.BrandPageResponse;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface BrandAdminService {

    Brand create(BrandRequest requestDto, MultipartFile brandImageFile);

    Brand findById(Long id);

    List<Brand> findAll();

    BrandPageResponse findAllPaging(int pageNo, int pageSize);

    List<Brand> findByStylePointId(Long id);

    Brand update(Long id, BrandRequest requestDto, MultipartFile brandImageFile);

    void delete(Long id);

}