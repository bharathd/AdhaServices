package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Photo;
import com.app.adha.util.UtilMethods;

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
	public List<Photo> getAllPhotosByUserId(int userId) {
		String hql = "FROM Photo as p where p.userId = :id";
		return (List<Photo>) entityManager.createQuery(hql).setParameter("id", userId).getResultList();
	}	
	
	@Override
	public void addPhoto(Photo photo) {
		entityManager.persist(photo);
	}
	
	@Override
	public void deletePhoto(int photoId) {
		String update_query = "update Photo set status = :status where id = :id";
		entityManager.createQuery(update_query).setParameter("status", UtilMethods.DELETE).setParameter("id", photoId).executeUpdate();
	}

}
