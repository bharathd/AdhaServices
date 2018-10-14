package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.PhotoDAO;
import com.app.adha.entity.Photo;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoDAO photoDAO;
	
	@Override
	public Photo getPhotoById(int photoId) {
		Photo photo = photoDAO.getPhotoById(photoId);
		return photo;
	}	
	
	@Override
	public List<Photo> getAllPhotos(){
		return photoDAO.getAllPhotos();
	}
	
	@Override
	public void addPhoto(Photo photo){
		photoDAO.addPhoto(photo);
	}


}
