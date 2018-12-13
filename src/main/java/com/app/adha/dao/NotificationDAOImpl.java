package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Notification;
import com.app.adha.util.UtilMethods;


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
	public List<Notification> getAllNotifications(int userId) {
		List<Notification> resultList = (List<Notification>) entityManager.createQuery("from Notification where toId = :userId and status = :status").setParameter("userId", userId).setParameter("status", UtilMethods.ACTIVE).getResultList();
		return resultList;
	}	
	
	@Override
	public void addNotification(Notification notification) {
		entityManager.persist(notification);
	}
	
	@Override
	public void updateNotificationStatus(int notificationId) {
		String update_query = "update Notification set status = :status where id = :id";
		entityManager.createQuery(update_query).setParameter("status", UtilMethods.NO).setParameter("id", notificationId).executeUpdate();
	}

}
