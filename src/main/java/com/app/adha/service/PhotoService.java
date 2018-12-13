package com.app.adha.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.app.adha.entity.Photo;

public interface PhotoService {
	
	List<Photo> getAllPhotosByUserId(int userId);
	byte[] getPhotosByUserId(String photo_url);
	Photo getPhotoById(int photoId);
    void addPhoto(Photo photo);
    void deletePhoto(int photoId);
    String storeFile(MultipartFile file, String newname);
}
