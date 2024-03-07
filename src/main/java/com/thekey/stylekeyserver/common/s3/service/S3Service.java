package com.thekey.stylekeyserver.common.s3.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.thekey.stylekeyserver.common.s3.S3ErrorMessage;
import com.thekey.stylekeyserver.image.domain.Image;
import com.thekey.stylekeyserver.image.domain.Type;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
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

    @Value("${cloud.aws.s3.brand}")
    private String brandFolder;

    @Value("${cloud.aws.s3.coordinateLook}")
    private String coordinateLookFolder;

    @Value("${cloud.aws.s3.item}")
    private String itemFolder;

    public Image uploadFile(MultipartFile file, Type imageType) throws IOException {
        String fileName = generateFileName(file, imageType);

        if (s3Client.doesObjectExist(bucket, fileName)) {
            throw new FileAlreadyExistsException(S3ErrorMessage.FILE_ALREADY_EXISTS.getMessage());
        }

        File convertedFile = convertMultiPartToFile(file);
        uploadFileTos3bucket(fileName, convertedFile);
        String url = getFileUrl(fileName);

        return Image.builder()
                .url(url)
                .type(imageType)
                .fileName(fileName)
                .isUsed(true)
                .build();
    }

    public void deleteFile(String fileUrl, Type imageType)
            throws AmazonServiceException, UnsupportedEncodingException, MalformedURLException {
        URL url = new URL(fileUrl);
        String fileName = url.getPath();
        String fullFileName = fileName.substring(1);
        String decodedFullFileName = URLDecoder.decode(fullFileName, StandardCharsets.UTF_8.toString());
        s3Client.deleteObject(new DeleteObjectRequest(bucket, decodedFullFileName));
    }

    private String getFolderName(Type imageType) {
        switch (imageType) {
            case BRAND:
                return brandFolder;
            case COORDINATE_LOOK:
                return coordinateLookFolder;
            case ITEM:
                return itemFolder;
            default:
                return "";
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());

        // FileOutputStream을 닫아야 파일이 닫힘
        // 만약 파일이 계속 열러있으면 삭제가 안 될 수 있음
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart, Type imageType) {
        return new StringBuilder()
                .append(imageType.getName())
                .append("/")
                .append(multiPart.getOriginalFilename())
                .toString();
    }

    private void uploadFileTos3bucket(String fileName, File file) throws FileAlreadyExistsException {
        if (s3Client.doesObjectExist(bucket, fileName)) {
            throw new FileAlreadyExistsException(S3ErrorMessage.FILE_ALREADY_EXISTS.getMessage() + fileName);
        }

        s3Client.putObject(new PutObjectRequest(bucket, fileName, file));
        // s3에 객체 생성 시 임시 파일이 자동으로 남는다.
        if (!file.delete()) {
            throw new RuntimeException(S3ErrorMessage.FILE_UPLOAD_FAILED.getMessage());
        }
    }

    private String getFileUrl(String fileName) {
        return s3Client.getUrl(bucket, fileName).toString();
    }
}