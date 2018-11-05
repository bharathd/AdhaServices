package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.PhotoDAO;
import com.app.adha.entity.Photo;
import com.app.adha.util.UtilMethods;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoDAO photoDAO;
	
	@Autowired
	private UtilMethods utilMethod;
	
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


}
