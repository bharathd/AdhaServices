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
import java.util.List;

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
	
	//Save the uploaded file to this folder
   // private static String UPLOADED_FOLDER = "./photos/";
    private final Path UPLOADED_FOLDER = Paths.get("src/main/resources/photos");
	
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
	public void addPhoto(Photo photo){
		photo.setUplodedDate(df.format(dateobj));
		photo.setStatus(UtilMethods.ACTIVE);
		photoDAO.addPhoto(photo);
	}
	
	public List<Resource> loadFile(int userId) {
		List<Resource>  array_resource = new ArrayList<Resource>();
		Path userid_path = Paths.get(UPLOADED_FOLDER + "/" + userId);
		List<Photo>  photos= photoDAO.getAllPhotosByUserId(userId);
		for( Photo user_photo:photos) {
		try {
			Path file = userid_path.resolve(user_photo.getPhotoURL());
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				array_resource.add(resource);
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
		}
		return array_resource;
	}
	
	@Override
	public void deletePhoto(int photoId) {
		photoDAO.deletePhoto(photoId);
	}

	@Override
	public String storeFile(MultipartFile file, String newname) {
		
    	String[] parts = newname.split("_");
    	
    	Path userid_path = Paths.get(UPLOADED_FOLDER + "/" + parts[0]);
    	
    	
    	
    	/*if (Files.notExists(userid_path)) {
    		try {
				Files.createDirectories(userid_path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		}
    	 
    	try {
    	byte[] bytes = file.getBytes();
        Path path = Paths.get(userid_path + "/" + file.getOriginalFilename());
        Files.write(path, bytes);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}*/
        
        
      AWSCredentialsProvider  awsCreds = new AWSStaticCredentialsProvider(new BasicAWSCredentials(UtilMethods.myAccessKey, UtilMethods.mySecretKey));
      AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCreds)
                .withEndpointConfiguration(new EndpointConfiguration(UtilMethods.endpoint, UtilMethods.region))
                .build();
      String filepath = file.getOriginalFilename();
      File s3_file = new File(filepath);
      PutObjectRequest por = new PutObjectRequest("adahappsql","test-Anil", s3_file);
      s3Client.putObject(por);
      
        Photo photo = new Photo();
        photo.setUserId(Integer.parseInt(parts[0]));
        photo.setUplodedBy(Integer.parseInt(parts[1]));
        photo.setProfilePhoto(Integer.parseInt(parts[2]));
        photo.setPhotoURL(userid_path + "/" + file.getOriginalFilename());
        
        addPhoto(photo);
        
        String description = "Admin Added Your Photo in the Adah Services";
        notificationService.addNotification(photo.getUplodedBy(), photo.getUserId(), description);
        
        return "Success";
	}
}
