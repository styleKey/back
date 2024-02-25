package com.thekey.stylekeyserver.brand.service;

import com.thekey.stylekeyserver.brand.BrandErrorMessage;
import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import com.thekey.stylekeyserver.brand.repository.BrandRepository;
import com.thekey.stylekeyserver.s3.S3Service;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.stylepoint.service.StylePointAdminService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandAdminServiceImpl implements BrandAdminService {

    private final BrandRepository brandRepository;
    private final StylePointAdminService stylePointAdminService;
    private final S3Service s3Service;

    @Override
    public Brand create(BrandRequest requestDto, MultipartFile imageFile) throws Exception {
        String imageUrl = null;
        imageUrl = s3Service.uploadFile(imageFile, "brand");
        StylePoint stylePoint = stylePointAdminService.findById(requestDto.getStylePointId());
        return brandRepository.save(requestDto.toEntity(stylePoint, imageUrl));
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
    public Brand update(Long id, BrandRequest requestDto, MultipartFile imageFile) throws Exception {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BrandErrorMessage.NOT_FOUND_BRAND.get() + id));

        StylePoint stylePoint = stylePointAdminService.findById(requestDto.getStylePointId());

        String oldImageUrl = brand.getImageUrl();
        if (oldImageUrl != null) {
            s3Service.deleteFile(oldImageUrl);
            String newImageUrl = s3Service.uploadFile(imageFile, "brand");
            brand.updateImage(newImageUrl);
        }

        brand.update(requestDto.getTitle(),
                requestDto.getTitle_eng(),
                requestDto.getSite_url(),
                stylePoint);

        return brand;
    }

    @Override
    public void delete(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BrandErrorMessage.NOT_FOUND_BRAND.get() + id));
        String imageFile = brand.getImageUrl();
        s3Service.deleteFile(imageFile);
        brandRepository.deleteById(id);
    }

}
