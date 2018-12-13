package com.app.adha.service;

import com.app.adha.dao.UserDetailsDAO;
import com.app.adha.entity.User;
import com.app.adha.entity.UserDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    DateFormat dob = new SimpleDateFormat("yy/MM/dd");
    Date dateobj = new Date();
	
	@Override
	public void addUserDetails(UserDetails userDetails){
		userDetails.setUpdatedDate(df.format(dateobj));
		userDetailsDAO.addUserDetails(userDetails);
		String description = "Added details successfully";
        notificationService.addNotification(userDetails.getUserId(), userDetails.getUserId(), description);
		
	}

	@Override
	public UserDetails getUserDetailsById(int userId) {
		return (UserDetails) userDetailsDAO.getUserDetailsById(userId);
	}
	
	@Override
	public void updateUserDetails(UserDetails userDetails) {
		UserDetails usr_details = getUserDetailsById(userDetails.getUserId());
		
		usr_details.setName(userDetails.getName());
		usr_details.setUpdatedDate(df.format(dateobj));
		usr_details.setUpdatedBy(userDetails.getUpdatedBy());
		usr_details.setCity(userDetails.getCity());
		usr_details.setGender(userDetails.getGender());
		usr_details.setHeight(userDetails.getHeight());
		usr_details.setMailId(userDetails.getMailId());
		usr_details.setLoggedType(userDetails.getLoggedType());
		usr_details.setInstaId(userDetails.getInstaId());
		usr_details.setInstaFollower(userDetails.getInstaFollower());
		usr_details.setDateOfBirth(userDetails.getDateOfBirth());
		usr_details.setWeight(userDetails.getWeight());
		userDetailsDAO.updateUserDetails(userDetails);
		
		String description = "Updated details successfully";
        notificationService.addNotification(userDetails.getUserId(), userDetails.getUserId(), description);
	}
}
