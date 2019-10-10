package com.ascending.training.april.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.ascending.training.april.init.AppInitializer;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class FileServiceTest {

    @Autowired FileService fileService;
    @Autowired AmazonS3 amazonS3;
    @Autowired Logger logger;
    Bucket bucket;
    String fileName = "swift.jpg";
    MultipartFile multipartFile;
    String path;
    String bucketName = "cytcute";

    @Before
    public void setUp()throws IOException {
        if (!amazonS3.doesBucketExistV2(bucketName)) bucket = fileService.createBucket(bucketName);

        File file = new File("/Users/april/Documents/work/product pics/swift.jpg");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", file.getName(), "image/jpg", input);
        path = System.getProperty("user.dir") + File.separator + "temp";
//        从本地get new File
//        用mockmultipartfile transfer into multipart(get file input by fileinputsream)
//        path = System.getProperty("user.dir") + file.seperator + "temp";
    }

    @After
    public void tearDown(){
//        try {
//            if (amazonS3.doesBucketExistV2("cytcute")) fileService.deleteBucket("cytcute");
//        }catch (Exception e){
//            logger.info(e.getMessage());
//        }
    }

    @Test
    public void createBucket(){
        logger.info(String.format("The bucket %s: is created now! ", bucketName));
        boolean a = amazonS3.doesBucketExistV2(bucketName);
        assertTrue(a);
    }

    @Test
    public void getBucketByName(){
        AccessControlList acl = fileService.getBucket("cytcute");
        Assert.assertNotNull(acl);
    }

    @Test
    public void uploadFile() throws IOException {
        String url = fileService.uploadFile(bucketName, multipartFile);
//        String fileUrl = fileService.getFileUrl(bucketName, multipartFile.getOriginalFilename());
        Assert.assertNotNull(url);
    }

    @Test
    public void listFile(){
        ObjectListing list = fileService.listObjects(bucketName);
        List<S3ObjectSummary> objectSummaries = list.getObjectSummaries();
        System.out.println("This is the file list: ");
        for (int i = 0; i<objectSummaries.size(); i++){
            System.out.println(objectSummaries.get(i).getKey());
        }
        Assert.assertEquals(1, objectSummaries.size());
    }

    @Test
    public void saveFile(){
        boolean a = fileService.saveFile(multipartFile, path);
        assertTrue(a);
    }

    @Test
    public void deleteFile(){
        boolean b = fileService.deleteFile(bucketName, fileName);
        assertTrue(b);
    }

    @Test
    public void deleteBucket(){
        ObjectListing list = fileService.listObjects(bucketName);
        if (list == null) {
            fileService.deleteBucket(bucketName);
        }else{
            System.out.println("Let's do cleaning...");
            List<S3ObjectSummary> objectSummaries = list.getObjectSummaries();
            for (int i = 0; i<objectSummaries.size(); i++){
                String fileName = objectSummaries.get(i).getKey();
                fileService.deleteFile(bucketName, fileName);
            }
            fileService.deleteBucket(bucketName);
        }
        AccessControlList acl = fileService.getBucket(bucketName);
        Assert.assertNull(acl);
    }
}
