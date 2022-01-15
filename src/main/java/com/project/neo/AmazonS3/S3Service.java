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

/**
 * This class acts as the connection between an Amazon S3 Bucket where the sweat data is stored to our BabyService class.
 * The access key and secret key allowing access to the bucket are stored as secrets on Heroku which are then accessed as environment
 * variables below, while region is taken from the application.properties file in the resources folder.
 */
@Service
public class S3Service {
    private AmazonS3 s3client;

    private String accessKeyId = System.getenv("KEY_ID");

    private String secretKey = System.getenv("SECRET_KEY");

//    FOR UNIT TESTING:
//    @Value("${cloud.aws.credentials.access-key}")
//    private String accessKeyId;
//
//    @Value("${cloud.aws.credentials.secret-key}")
//    private String secretKey;

    @Value("${s3.region}")
    private String region;

    /**
     * This method initialises the connection with the S3 bucket.
     */
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

    /**
     * This method returns an S3Object containing the csv file of the baby's sweat data.
     * @param bucket_name Name of the bucket where data is stored.
     * @param file_name ID.csv of the specific baby.
     * @return Object containing the file data of the specific baby.
     */
    public S3Object getsweatData(String bucket_name, String file_name ) {
        return s3client.getObject(bucket_name, file_name);
    }


}

