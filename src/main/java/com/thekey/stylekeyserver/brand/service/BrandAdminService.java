package com.thekey.stylekeyserver.brand.service;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface BrandAdminService {

    Brand create(BrandRequest requestDto, MultipartFile imageFile) throws Exception;

    Brand findById(Long id);

    List<Brand> findAll();

    List<Brand> findByStylePointId(Long id);

    Brand update(Long id, BrandRequest requestDto, MultipartFile imageFile) throws Exception;

    void delete(Long id) throws MalformedURLException, UnsupportedEncodingException;

}