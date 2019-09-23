package com.ascending.training.april.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.ascending.training.april.init.AppInitializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceMockAWSTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS) private AmazonS3 amazonS3;
    @Autowired @Spy private Logger logger;
//    InjectMocks means the amazon S3 in fileService is injected with mocked not inject beans.
    @InjectMocks private FileService fileService;

    private String bucketName = "com_training_ascending_april_test";
    private String fileName = "image/jpg";
    private URL fakeFileUrl;
    private MultipartFile multipartFile;
    private String path;
    private Bucket bucket = new Bucket();
    private AccessControlList acl = new AccessControlList();
//    private PutObjectResult putObjectResult;
    private ObjectListing list = new ObjectListing();

    @Before
    public void setUp() throws MalformedURLException, FileNotFoundException, IOException {
        logger.info(">>>>>>>>> Start Testing... <<<<<<<<<");

        //Mockito are initialized before each test method
        MockitoAnnotations.initMocks(this);

        fakeFileUrl = new URL("http://www.fakeQueueUrl.com/abc/123/fake");
        File file = new File("/Users/april/Documents/work/product pics/swift.jpg");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", file.getName(), "image/jpg", IOUtils.toByteArray(input));
        path = System.getProperty("user.dir") + File.separator + "temp";

//        Stubbing for the method doesObjectExist and generatePresignedUrl
        when(amazonS3.doesBucketExistV2(anyString())).thenReturn(false);
        when(amazonS3.generatePresignedUrl(any())).thenReturn(fakeFileUrl);
    }

    @After
    public void tearDown(){
        logger.info(">>>>>>>>>> End Test <<<<<<<<<");
    }

    @Test
    public void createBucket(){
        when(amazonS3.createBucket(bucketName)).thenReturn(bucket);
        fileService.createBucket(bucketName);
        verify(amazonS3, times(1)).createBucket(anyString());
    }

    @Test
    public void getBucket(){
        when(amazonS3.doesBucketExistV2(anyString())).thenReturn(true);
        when(amazonS3.getBucketAcl(bucketName)).thenReturn(acl);
        fileService.getBucket(bucketName);
        verify(amazonS3, times(1)).getBucketAcl(anyString());
    }

    @Test
    public void deleteBucket(){
        when(amazonS3.doesBucketExistV2(anyString())).thenReturn(true);
        fileService.deleteBucket(bucketName);
        verify(amazonS3, times(1)).deleteBucket(anyString());
    }

    @Test
    public void getFileUrl(){
        String fileUrl = fileService.getFileUrl(bucketName, fileName);
        assertEquals(fileUrl, fakeFileUrl.toString());
        verify(amazonS3, times(1)).generatePresignedUrl(any());
    }

    @Test
    public void uploadFile()throws IOException{
        when(amazonS3.doesObjectExist(bucketName, multipartFile.getOriginalFilename())).thenReturn(false);
        String url = fileService.uploadFile(bucketName, multipartFile);
        Assert.assertNotNull(url);
        verify(amazonS3, times(1)).putObject(anyString(), anyString(), any(), any());
    }

    @Test
    public void saveFile(){
        boolean b = fileService.saveFile(multipartFile, path);
        Assert.assertTrue(b);
    }

    @Test
    public void listObject(){
        when(amazonS3.doesBucketExistV2(bucketName)).thenReturn(true);
        when(amazonS3.listObjects(bucketName)).thenReturn(list);
        fileService.listObjects(bucketName);
        verify(amazonS3, times(1)).listObjects(anyString());
    }

    @Test
    public void deleteFile(){
        boolean b = fileService.deleteFile(bucketName, fileName);
        Assert.assertFalse(b);
    }

}