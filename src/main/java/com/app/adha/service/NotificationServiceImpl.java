package com.app.adha.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.NotificationDAO;
import com.app.adha.entity.Notification;


@Service
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationDAO notificationDAO;
	
	@Override
	public Notification getNotificationById(int notificationId) {
		Notification notification = notificationDAO.getNotificationById(notificationId);
		return notification;
	}	
	
	@Override
	public List<Notification> getAllNotifications(){
		return notificationDAO.getAllNotifications();
	}
	
	@Override
	public void addNotification(Notification notification){
		notificationDAO.addNotification(notification);
	}

}
