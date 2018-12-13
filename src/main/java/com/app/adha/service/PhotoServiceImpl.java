package com.app.adha.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.net.MalformedURLException;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.adha.dao.PhotoDAO;
import com.app.adha.entity.Photo;
import com.app.adha.util.UtilMethods;


@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoDAO photoDAO;
	
	@Autowired
    private NotificationService notificationService;
	
	@Autowired
	private S3BucketUpload s3BucketUpload;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
    
	@Override
	public Photo getPhotoById(int photoId) {
		Photo photo = photoDAO.getPhotoById(photoId);
		return photo;
	}	
	
	@Override
	public List<Photo> getAllPhotosByUserId(int userId){
		return photoDAO.getAllPhotosByUserId(userId);
	}
	
	
	@Override
	public byte[] getPhotosByUserId(String photo_url){
		byte[] bytes = s3BucketUpload.downloadFile(photo_url);
		return bytes;
	}
	
	@Override
	public void addPhoto(Photo photo){
		photo.setUplodedDate(df.format(dateobj));
		photo.setStatus(UtilMethods.ACTIVE);
		photoDAO.addPhoto(photo);
	}
	
	
	
	@Override
	public void deletePhoto(int photoId) {
		photoDAO.deletePhoto(photoId);
	}

	@Override
	public String storeFile(MultipartFile file, String newname) {
		
    	String[] parts = newname.split("_");
    	String bucketName = "photos" + "/" + parts[0] + "/";
    	try {
    	s3BucketUpload.upload(file, bucketName);
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    		 
    	}
        Photo photo = new Photo();
        photo.setUserId(Integer.parseInt(parts[0]));
        photo.setUplodedBy(Integer.parseInt(parts[1]));
        photo.setProfilePhoto(Integer.parseInt(parts[2]));
        photo.setPhotoURL(bucketName + file.getOriginalFilename());
        
        if(Integer.parseInt(parts[2]) == UtilMethods.YES) {
        	List<Photo> pp_photos = photoDAO.getProfilePhotosByUserId(Integer.parseInt(parts[0]));
        	for(Photo p_photo:pp_photos) {
        		photoDAO.updateProfilePhoto(p_photo.getPhotoId());
        	}
        }
        
        addPhoto(photo);
        
        String description = "Admin Added Your Photo in the Adah Services";
        notificationService.addNotification(photo.getUplodedBy(), photo.getUserId(), description);
        
        return "Success";
	}
}
