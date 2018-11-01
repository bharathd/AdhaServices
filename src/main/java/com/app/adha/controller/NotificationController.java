package com.app.adha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.Notification;
import com.app.adha.service.NotificationService;

@CrossOrigin(origins = { "http://159.65.145.220:8080" }, maxAge = 3000)
@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> getNotificationById(@PathVariable("id") int id) {
        System.out.println("Fetching Notification with id " + id);
        Notification notification = notificationService.getNotificationById(id);
        if (notification == null) {
            return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    @GetMapping("notifications")
	public ResponseEntity<List<Notification>> getAllNotifications() {
		List<Notification> list = notificationService.getAllNotifications();
		return new ResponseEntity<List<Notification>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addnotification", headers="Accept=application/json")
	public ResponseEntity<Void> addNotification(@RequestBody Notification notification, UriComponentsBuilder ucBuilder) {
    	      notificationService.addNotification(notification);;
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/notification/{id}").buildAndExpand(notification.getNotificationId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
