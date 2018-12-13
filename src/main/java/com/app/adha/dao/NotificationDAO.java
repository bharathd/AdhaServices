package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Notification;

public interface NotificationDAO {
	
	List<Notification> getAllNotifications(int userId);
	Notification getNotificationById(int notificationId);
    void addNotification(Notification notification);
    void updateNotificationStatus(int notificationId);

}
