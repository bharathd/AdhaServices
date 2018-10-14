package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Photo;

@Transactional
@Repository
public class PhotoDAOImpl implements PhotoDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public Photo getPhotoById(int photoId) {
		return entityManager.find(Photo.class, photoId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> getAllPhotos() {
		String hql = "FROM Otp as o ORDER BY o.userId";
		return (List<Photo>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addPhoto(Photo photo) {
		entityManager.persist(photo);
	}

}