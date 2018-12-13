package com.app.adha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.Notification;
import com.app.adha.service.NotificationService;

@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> getNotificationById(@PathVariable("id") int id) {
		logger.info("Fetching Notification with id " + id);
        Notification notification = notificationService.getNotificationById(id);
        if (notification == null) {
            return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    @GetMapping(value = "/userid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Notification>> getAllNotifications(@PathVariable("id") int userId) {
		List<Notification> list = notificationService.getAllNotifications(userId);
		logger.info("Fetching All Notifications" + list);
		return new ResponseEntity<List<Notification>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addnotification", headers="Accept=application/json")
	public ResponseEntity<Void> addNotification(@RequestBody Notification notification, UriComponentsBuilder ucBuilder) {
    	      notificationService.addNotification(notification.getFromId(), notification.getToId(), notification.getDescription());
    	      logger.info("Add Notification" + notification);
               HttpHeaders headers = new HttpHeaders();
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping(value="/updatenotification/{id}")
	public ResponseEntity<Void> updateNotification(@PathVariable("id") Integer notificationId) {
    	      notificationService.updateNotificationStatus(notificationId);
    	      logger.info("Update Notification" + notificationId);
               HttpHeaders headers = new HttpHeaders();
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
