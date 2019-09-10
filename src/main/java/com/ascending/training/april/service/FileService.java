package com.ascending.training.april.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileService {
    @Autowired private Logger logger;
    @Autowired private AmazonS3 amazonS3;

    public String uploadFile(String bucketName, MultipartFile file) throws IOException {
        try {
            if (amazonS3.doesObjectExist(bucketName, file.getOriginalFilename())) {
                logger.info(String.format("The file '%s' exists in the bucket %s", file.getOriginalFilename(), bucketName));
                return null;
            }
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), objectMetadata);
            logger.info(String.format("The file name=%s, size=%d was uploaded to bucket %s", file.getOriginalFilename(), file.getSize(), bucketName));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return getFileUrl(bucketName, file.getOriginalFilename());
    }

//    fileName is key
    public String getFileUrl(String bucketName, String fileName){
        LocalDateTime expiration = LocalDateTime.now().plusDays(3);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)));

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public void createBucket(String bucketName){
        if (!amazonS3.doesBucketExistV2(bucketName)) amazonS3.createBucket(bucketName);
    }

    public boolean saveFile(MultipartFile multipartFile, String filePath){
        boolean isSuccess = false;

        try {
            File directory = new File(filePath);
            if (!directory.exists()) directory.mkdir();
            Path filepath = Paths.get(filePath,multipartFile.getOriginalFilename());
            multipartFile.transferTo(filepath);
            isSuccess = true;
            logger.info(String.format("The file %s is saved in the folder %s", multipartFile.getOriginalFilename(), filePath));
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return isSuccess;
    }
}
