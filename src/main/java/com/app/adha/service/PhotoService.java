package com.app.adha.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.adha.entity.Photo;

public interface PhotoService {
	
	List<Photo> getAllPhotosByUserId(int userId);
	Photo getPhotoById(int photoId);
    void addPhoto(Photo photo);
    void deletePhoto(int photoId);
    String storeFile(MultipartFile file, String newname);
}
