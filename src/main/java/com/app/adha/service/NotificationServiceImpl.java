package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.NotificationDAO;
import com.app.adha.entity.Notification;
import com.app.adha.util.UtilMethods;


@Service
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationDAO notificationDAO;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date dateobj = new Date();
	
	@Override
	public Notification getNotificationById(int notificationId) {
		Notification notification = notificationDAO.getNotificationById(notificationId);
		return notification;
	}	
	
	@Override
	public List<Notification> getAllNotifications(int userId){
		return notificationDAO.getAllNotifications(userId);
	}
	
	@Override
	public void addNotification(int fromId, int toId, String description){
		Notification notification = new Notification();
		notification.setFromId(fromId);
		notification.setToId(toId);
		notification.setDescription(description);
		notification.setStatus(UtilMethods.ACTIVE);
		notification.setCreatedDate(df.format(dateobj));
		notificationDAO.addNotification(notification);
	}
	
	@Override
	public void updateNotificationStatus(int notificationId) {
		notificationDAO.updateNotificationStatus(notificationId);
	}	

}
