package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Notification;

@Transactional
@Repository
public class NotificationDAOImpl implements NotificationDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public Notification getNotificationById(int notificationId) {
		return entityManager.find(Notification.class, notificationId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getAllNotifications() {
		String hql = "FROM Otp as o ORDER BY o.userId";
		return (List<Notification>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addNotification(Notification notification) {
		entityManager.persist(notification);
	}

}
