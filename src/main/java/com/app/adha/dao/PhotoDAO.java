package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Photo;

public interface PhotoDAO {
	
	List<Photo> getAllPhotosByUserId(int userId);
	Photo getPhotoById(int userId);
    void addPhoto(Photo photo);
    void deletePhoto(int photoId);
}
