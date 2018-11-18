package com.app.adha.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    private static String UPLOADED_FOLDER = "./photos/";
	
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
	
	@Override
	public void deletePhoto(int photoId) {
		photoDAO.deletePhoto(photoId);
	}

	@Override
	public String storeFile(MultipartFile file) {
		UPLOADED_FOLDER = "./photos/";
    	String[] parts = file.getOriginalFilename().split("_");
    	
    	Path folder_path = Paths.get(UPLOADED_FOLDER + parts[0]);
    	UPLOADED_FOLDER = UPLOADED_FOLDER + parts[0] + "/";
    	if (Files.notExists(folder_path)) {
    		try {
				Files.createDirectories(folder_path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		}
    	
    	try {
    	byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        
        
        
        Photo photo = new Photo();
        photo.setUserId(Integer.parseInt(parts[0]));
        photo.setUplodedBy(Integer.parseInt(parts[1]));
        photo.setProfilePhoto(Integer.parseInt(parts[2]));
        photo.setPhotoURL(UPLOADED_FOLDER + file.getOriginalFilename());
        
        addPhoto(photo);
        
        String description = "Admin Added Your Photo in the Adah Services";
        notificationService.addNotification(photo.getUplodedBy(), photo.getUserId(), description);
        
        return "Sucess";
	}
}
