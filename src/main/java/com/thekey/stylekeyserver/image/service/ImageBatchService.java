package com.thekey.stylekeyserver.image.service;

import com.thekey.stylekeyserver.image.domain.Image;
import com.thekey.stylekeyserver.image.repository.ImageRepository;
import com.thekey.stylekeyserver.common.s3.S3Service;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class ImageBatchService {
    private final ImageRepository imageRepository;
    private final S3Service s3Service;

//    @Scheduled(cron = "0 0 0 * * *")  // 매일 새벽 0시에 실행
//    public void deleteUnusedImages() throws MalformedURLException, UnsupportedEncodingException {
//        List<Image> unusedImages = imageRepository.findByIsUsed(false);
//
//        for (Image image : unusedImages) {
//            // 이미지가 24시간 이상 사용되지 않았다면 S3에서 이미지 파일 삭제
//            if (image.getDeletedAt().isBefore(LocalDateTime.now())) {
//                s3Service.deleteFile(image.getUrl(), image.getType());
//                imageRepository.delete(image);
//            }
//        }
//    }
}