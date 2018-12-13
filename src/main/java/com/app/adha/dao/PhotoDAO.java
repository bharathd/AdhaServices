package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Photo;

public interface PhotoDAO {
	
	List<Photo> getAllPhotosByUserId(int userId);
	List<Photo> getProfilePhotosByUserId(int userId);
	Photo getPhotoById(int userId);
    void addPhoto(Photo photo);
    void updateProfilePhoto(int photoId);
    void deletePhoto(int photoId);
}
