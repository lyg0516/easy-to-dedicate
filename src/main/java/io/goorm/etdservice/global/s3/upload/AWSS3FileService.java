package io.goorm.etdservice.global.s3.upload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import io.goorm.etdservice.global.exception.DomainException;
import io.goorm.etdservice.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AWSS3FileService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void putObject(MultipartFile file,String key ,String filename) throws DomainException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        try {
            amazonS3Client.putObject(bucket, key+ "/" + filename, file.getInputStream(), objectMetadata);
        } catch (IOException e) {
            throw new DomainException(ErrorCode.INVALID_IMAGE_FILE);
        }
    }
}
