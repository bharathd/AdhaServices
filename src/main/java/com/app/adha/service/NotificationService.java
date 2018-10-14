package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Notification;

public interface NotificationService {
	
	List<Notification> getAllNotifications();
	Notification getNotificationById(int notificationId);
    void addNotification(Notification notification);

}
