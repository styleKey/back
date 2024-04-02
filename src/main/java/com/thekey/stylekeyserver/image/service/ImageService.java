package com.thekey.stylekeyserver.image.service;

import com.thekey.stylekeyserver.common.s3.service.S3Service;
import com.thekey.stylekeyserver.image.entity.Image;
import com.thekey.stylekeyserver.image.repository.ImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final S3Service s3Service;

    public void deleteUnusedImages() {
        List<Image> unusedImages = imageRepository.findByIsUsed(false);

        for (Image image : unusedImages) {
            // 이미지를 S3에서 즉시 삭제
            s3Service.deleteFile(image.getUrl());
            imageRepository.delete(image);
        }

    }
}
