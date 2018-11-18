package com.app.adha.service;

import com.app.adha.dao.UserDetailsDAO;
import com.app.adha.entity.User;
import com.app.adha.entity.UserDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	public UserDetailsDAO userDetailsDAO; 
	
	@Autowired
    private NotificationService notificationService;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
	
	@Override
	public void addUserDetails(UserDetails userDetails){
		userDetails.setUpdatedDate(df.format(dateobj));
		userDetailsDAO.addUserDetails(userDetails);
		String description = "Added details successfully";
        notificationService.addNotification(userDetails.getUserId(), userDetails.getUserId(), description);
		
	}

	@Override
	public List<UserDetails> getUserDetailsById(int userId) {
		List<UserDetails> obj = userDetailsDAO.getUserDetailsById(userId);
		return obj;
	}
	
	@Override
	public void updateUserDetails(UserDetails userDetails) {
		List<UserDetails> usr_details = getUserDetailsById(userDetails.getUserId());
		for(UserDetails details: usr_details) {
		details.setName(userDetails.getName());
		details.setUpdatedDate(df.format(dateobj));
		details.setUpdatedBy(userDetails.getUpdatedBy());
		details.setCity(userDetails.getCity());
		details.setGender(userDetails.getGender());
		details.setHeight(userDetails.getHeight());
		details.setMailId(userDetails.getMailId());
		details.setGender(userDetails.getGender());
		details.setInstaId(userDetails.getInstaId());
		details.setInstaFollower(userDetails.getInstaFollower());
		}
		
		userDetailsDAO.updateUserDetails(userDetails);
		
		String description = "Updated details successfully";
        notificationService.addNotification(userDetails.getUserId(), userDetails.getUserId(), description);
	}
}
