package com.project.neo.AmazonS3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class S3Service {
    private AmazonS3 s3client;

    //@Value("${s3.accessKeyId}")
    private String accessKeyId = System.getenv("KEY_ID");

    //@Value("${s3.secretKey}")
    private String secretKey = System.getenv("SECRET_KEY");

    @Value("${s3.region}")
    private String region;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials
                = new BasicAWSCredentials(this.accessKeyId, this.secretKey);
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public S3Object getsweatData(String bucket_name, String file_name ) {
        return s3client.getObject(bucket_name, file_name);
    }


}

