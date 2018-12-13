package com.app.adha.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.activation.MimetypesFileTypeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;


@Service
public class S3BucketUpload {
	
	private static final Logger log = LoggerFactory.getLogger(S3BucketUpload.class);

	
	
	String myAccessKey = "2OXRQVNZN46NEA2NDKMA";
	String mySecretKey = "jo0hEQbM4VpP8izGxl3mUYVXJQlxH56RrJCQcKrWTz8";

	public void upload(MultipartFile multiPartFile, String bucketName) throws IOException {
		try {
			AWSCredentialsProvider awsCreds = new AWSStaticCredentialsProvider(
					new BasicAWSCredentials(myAccessKey, mySecretKey));
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(awsCreds)
					.withEndpointConfiguration(new EndpointConfiguration("https://sfo2.digitaloceanspaces.com", "sfo2"))
					.build();

			
			
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(new MimetypesFileTypeMap().getContentType(multiPartFile.getContentType()));
		System.out.println("Anil"+bucketName);
			PutObjectRequest request = new PutObjectRequest("adahappsql", bucketName.concat(multiPartFile.getOriginalFilename()),multiPartFile.getInputStream(),metadata);
			
			
			s3Client.putObject(request);
			
		} catch(AmazonServiceException ase) {
			log.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
			log.info("Error Message:    " + ase.getMessage());
			log.info("HTTP Status Code: " + ase.getStatusCode());
			log.info("AWS Error Code:   " + ase.getErrorCode());
			log.info("Error Type:       " + ase.getErrorType());
			log.info("Request ID:       " + ase.getRequestId());
        } catch(AmazonClientException ace) {
        	log.info("Caught an AmazonClientException: ");
        	log.info("Error Message: " + ace.getMessage());
        }
	}

	
public byte[] downloadFile(String keyName) {
		
		try {
			
			AWSCredentialsProvider awsCreds = new AWSStaticCredentialsProvider(
					new BasicAWSCredentials(myAccessKey, mySecretKey));
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(awsCreds)
					.withEndpointConfiguration(new EndpointConfiguration("https://sfo2.digitaloceanspaces.com", "sfo2"))
					.build();
			
			
			S3Object s3object = s3Client.getObject(new GetObjectRequest("adahappsql", keyName));
            
            System.out.println("Content-Type: "  + s3object.getObjectMetadata().getContentType());
            S3ObjectInputStream objectContent = s3object.getObjectContent();
            try {
				
            	return StreamUtils.copyToByteArray(objectContent);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
        } catch (AmazonServiceException ase) {
        	log.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
        	log.info("Error Message:    " + ase.getMessage());
        	log.info("HTTP Status Code: " + ase.getStatusCode());
        	log.info("AWS Error Code:   " + ase.getErrorCode());
        	log.info("Error Type:       " + ase.getErrorType());
        	log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
        	log.info("Caught an AmazonClientException: ");
        	log.info("Error Message: " + ace.getMessage());
        } 
		return new byte[0];
	}
	
}