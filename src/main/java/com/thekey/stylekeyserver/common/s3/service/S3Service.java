package com.thekey.stylekeyserver.common.s3.service;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.FILE_ALREADY_EXISTS;
import static com.thekey.stylekeyserver.common.exception.ErrorCode.FILE_UPLOAD_FAILED;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.thekey.stylekeyserver.common.exception.ApiException;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import com.thekey.stylekeyserver.image.domain.Image;
import com.thekey.stylekeyserver.image.domain.Type;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Image uploadFile(MultipartFile file, Type imageType) {
        String fileName = generateFileName(file, imageType);

        if (s3Client.doesObjectExist(bucket, fileName)) {
            throw new ApiException(FILE_ALREADY_EXISTS);
        }

        try {
            File convertedFile = convertMultiPartToFile(file);
            uploadFileTos3bucket(fileName, convertedFile);
            String url = getFileUrl(fileName);

            return Image.builder()
                    .url(url)
                    .type(imageType)
                    .fileName(fileName)
                    .isUsed(true)
                    .build();

        } catch (IOException e) {
            throw new ApiException(FILE_UPLOAD_FAILED);
        }
    }

    public void deleteFile(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            String fileName = url.getPath();
            String fullFileName = fileName.substring(1);
            String decodedFullFileName = URLDecoder.decode(fullFileName, StandardCharsets.UTF_8);
            s3Client.deleteObject(new DeleteObjectRequest(bucket, decodedFullFileName));
        } catch (MalformedURLException e) {
            throw new ApiException(ErrorCode.FAIL_FILE_DELETE);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart, Type imageType) {
        return imageType.getName() + "/" + multiPart.getOriginalFilename();
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3Client.putObject(new PutObjectRequest(bucket, fileName, file));
        if (!file.delete()) {
            throw new ApiException(FILE_UPLOAD_FAILED);
        }
    }

    private String getFileUrl(String fileName) {
        return s3Client.getUrl(bucket, fileName).toString();
    }
}