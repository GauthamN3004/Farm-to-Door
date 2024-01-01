package com.farm_to_door.farm2door_API.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import software.amazon.awssdk.services.s3.S3Client;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ImageController {

    @Value("${aws.s3.access.key}")
    private String accessKey;

    @Value("${aws.s3.secret.key}")
    private String secretKey;

    @PostMapping("/files/upload")
    public ResponseEntity<?> handleFileUpload(@RequestPart("file") MultipartFile multipartFile) {
        try {
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType(multipartFile.getContentType());
            data.setContentLength(multipartFile.getSize());
            String filename = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
            BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).withCredentials(new AWSStaticCredentialsProvider(creds)).build();
            PutObjectResult objectResult = s3client.putObject("farm-to-door", filename, multipartFile.getInputStream(), data);
            return ResponseEntity.ok().body(filename);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }
}
