package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Photo;

public interface PhotoService {
	
	List<Photo> getAllPhotos();
	Photo getPhotoById(int photoId);
    void addPhoto(Photo photo);
    void deletePhoto(int photoId);
}
