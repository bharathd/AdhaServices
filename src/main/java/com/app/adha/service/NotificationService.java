package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Notification;

public interface NotificationService {
	
	List<Notification> getAllNotifications(int userId);
	Notification getNotificationById(int notificationId);
    void addNotification(int fromId, int toId, String description);

}
