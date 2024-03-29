package com.ascending.training.april.controller;

import com.ascending.training.april.service.FileService;
import com.ascending.training.april.service.MessageService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    private static final String queueName = "cytsocute1";
    private static final String fileDownloadDir = "/Users/april/Downloads";
    @Autowired private Logger logger;
    @Autowired private FileService fileService;
    @Autowired private MessageService messageService;

    @RequestMapping(value = "/{bucketName}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@PathVariable String bucketName, @RequestParam("file") MultipartFile file) {
        logger.info(file.getOriginalFilename());
        String msg = String.format("The file name=%s, size=%d could not be uploaded.", file.getOriginalFilename(), file.getSize());
        ResponseEntity responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(msg);
        try {
            String path = System.getProperty("user.dir") + File.separator + "temp";
            fileService.saveFile(file, path);
            String url = fileService.uploadFile(bucketName, file);
            if (url != null) {
                msg = String.format("The file name=%s, size=%d was uploaded, url=%s", file.getOriginalFilename(), file.getSize(), url);
                messageService.sendMessage(queueName, url);
                responseEntity = ResponseEntity.status(HttpServletResponse.SC_OK).body(msg);
            }
            logger.info(msg);
        } catch (Exception e) {
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(e.getMessage());
            logger.error(e.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/multi/{bucketName}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@PathVariable String bucketName, @RequestParam("file") MultipartFile[] file) {
        String msg = "initializing...";
        ResponseEntity responseEntity = ResponseEntity.status(HttpServletResponse.SC_NO_CONTENT).body(msg);
        for (int i = 0; i < file.length; i++) {
            logger.info(file[i].getOriginalFilename());

            msg = String.format("The file name=%s, size=%d could not be uploaded.", file[i].getOriginalFilename(), file[i].getSize());
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(msg);
            try {
                String path = System.getProperty("user.dir") + File.separator + "temp";
                fileService.saveFile(file[i], path);
                String url = fileService.uploadFile(bucketName, file[i]);
                if (url != null) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>start...<<<<<<<<<<<<<<<<<<<<");
                    msg = String.format("The file name=%s, size=%d was uploaded, url=%s", file[i].getOriginalFilename(), file[i].getSize(), url);
                    messageService.sendMessage(queueName, url);
                    responseEntity = ResponseEntity.status(HttpServletResponse.SC_OK).body(msg);
                }
                logger.info(msg);
            } catch (Exception e) {
                responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = null;
        String msg = "The file doesn't exist.";
        ResponseEntity responseEntity;
        try {
            Path filePath = Paths.get(fileDownloadDir).toAbsolutePath().resolve(fileName).normalize();
            resource = new UrlResource(filePath.toUri());
            if(!resource.exists()) return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(msg);
            responseEntity = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
            msg = String.format("The file %s was downloaded", resource.getFilename());
            //Send message to SQS
            messageService.sendMessage(queueName, msg);
            logger.debug(msg);
        }
        catch (Exception ex) {
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(ex.getMessage());
            logger.debug(ex.getMessage());
        }
        return responseEntity;
    }
}
