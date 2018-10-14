package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Photo;

public interface PhotoDAO {
	
	List<Photo> getAllPhotos();
	Photo getPhotoById(int photoId);
    void addPhoto(Photo photo);

}
