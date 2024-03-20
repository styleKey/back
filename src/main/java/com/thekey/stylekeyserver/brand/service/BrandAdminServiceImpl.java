package com.thekey.stylekeyserver.brand.service;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.BRAND_NOT_FOUND;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.dto.request.BrandRequest;
import com.thekey.stylekeyserver.brand.dto.response.BrandPageResponse;
import com.thekey.stylekeyserver.brand.dto.response.BrandResponse;
import com.thekey.stylekeyserver.brand.repository.BrandRepository;
import com.thekey.stylekeyserver.common.exception.ApiException;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import com.thekey.stylekeyserver.image.domain.Image;
import com.thekey.stylekeyserver.image.domain.Type;
import com.thekey.stylekeyserver.image.repository.ImageRepository;
import com.thekey.stylekeyserver.common.s3.service.S3Service;
import com.thekey.stylekeyserver.image.service.ImageService;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.stylepoint.service.StylePointAdminService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BrandAdminServiceImpl implements BrandAdminService {

    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;
    private final StylePointAdminService stylePointAdminService;
    private final ImageService imageService;
    private final S3Service s3Service;

    @Override
    @Transactional
    public Brand create(BrandRequest requestDto, MultipartFile brandImageFile) {
        validationImageFile(brandImageFile);

        Image image = s3Service.uploadFile(brandImageFile, Type.BRAND);
        imageRepository.save(image);
        StylePoint stylePoint = stylePointAdminService.findById(requestDto.getStylePointId());

        Brand brand = requestDto.toEntity(stylePoint);
        brand.setImage(image);
        return brandRepository.save(brand);
    }

    @Override
    @Transactional(readOnly = true)
    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BRAND_NOT_FOUND.getMessage()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BrandPageResponse findAllPaging(Pageable pageable) {
        Page<Brand> brandPage = brandRepository.findAll(pageable);

        List<BrandResponse> brandResponses = brandPage.getContent().stream()
                .map(BrandResponse::of)
                .toList();
        return BrandPageResponse.of(brandResponses, brandPage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brand> findByStylePointId(Long id) {
        StylePoint stylePoint = stylePointAdminService.findById(id);
        return brandRepository.findBrandByStylePoint(stylePoint);
    }

    @Override
    @Transactional
    public Brand update(Long id, BrandRequest requestDto, MultipartFile brandImageFile) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BRAND_NOT_FOUND.getMessage()));

        StylePoint stylePoint = stylePointAdminService.findById(requestDto.getStylePointId());

        // 수정 할 이미지가 요청값에 포함 되어있을 때만 기존 이미지에서 수정할 이미지로 변경
        if (brandImageFile != null && !brandImageFile.isEmpty()) {
            Image oldImage = brand.getImage();
            if (oldImage != null) {
                oldImage.setUnused();
                imageRepository.save(oldImage);
                imageService.deleteUnusedImages();

                Image newImage = s3Service.uploadFile(brandImageFile, Type.BRAND);
                imageRepository.save(newImage);
                brand.setImage(newImage);
                brandRepository.save(brand);
            }
        }

        // 수정 할 이미지가 없다면 기본 정보만 변경
        brand.update(requestDto.getTitle(),
                requestDto.getTitle_eng(),
                requestDto.getSite_url(),
                stylePoint);

        return brand;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BRAND_NOT_FOUND.getMessage()));

        Image image = brand.getImage();

        if (image != null) {
            image.setUnused();
            imageRepository.save(image);
            imageService.deleteUnusedImages();
        }
        brandRepository.deleteById(id);
    }

    private void validationImageFile(MultipartFile brandImageFile) {
        if (brandImageFile == null || brandImageFile.isEmpty()) {
            throw new ApiException(ErrorCode.FILE_NOT_FOUND);
        }
    }

}