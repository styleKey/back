package com.thekey.stylekeyserver.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public String uploadFile(MultipartFile file, String imageType) throws IOException {
        String folderName = getFolderName(imageType);
        String fileName = generateFileName(file, folderName);

        // 동일한 이름의 파일이 이미 존재하는지 확인한다.
        if (s3Client.doesObjectExist(bucket, fileName)) {
            throw new FileAlreadyExistsException(S3ErrorMessage.FILE_ALREADY_EXISTS.get());
        }

        File convertedFile = convertMultiPartToFile(file);
        uploadFileTos3bucket(fileName, convertedFile);
        return getFileUrl(fileName);
    }


    public void deleteFile(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String getFolderName(String imageType) {
        switch (imageType) {
            case "brand":
                return brandFolder;
            case "coordinateLook":
                return coordinateLookFolder;
            case "item":
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

    private String generateFileName(MultipartFile multiPart, String folderName) {
        return new StringBuilder()
                .append(folderName)
                .append(multiPart.getOriginalFilename())
                .toString();
    }

    private void uploadFileTos3bucket(String fileName, File file) throws FileAlreadyExistsException {
        if (s3Client.doesObjectExist(bucket, fileName)) {
            throw new FileAlreadyExistsException(S3ErrorMessage.FILE_ALREADY_EXISTS.get() + fileName);
        }

        s3Client.putObject(new PutObjectRequest(bucket, fileName, file));
        // s3에 객체 생성 시 임시 파일이 자동으로 남는다.
        if (!file.delete()) {
            throw new RuntimeException(S3ErrorMessage.FILE_UPLOAD_FAILED.get());
        }
    }

    private String getFileUrl(String fileName) {
        return s3Client.getUrl(bucket, fileName).toString();
    }
}
