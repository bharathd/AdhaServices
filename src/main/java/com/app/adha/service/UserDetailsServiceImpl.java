package com.app.adha.service;

import com.app.adha.dao.UserDetailsDAO;
import com.app.adha.entity.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	public UserDetailsDAO userDetailsDAO; 
	

	@Override
	public UserDetails getUserDetailsById(int userId) {
		UserDetails obj = userDetailsDAO.getUserDetailsById(userId);
		return obj;
	}
	
	@Override
	public void updateUserDetails(UserDetails userDetails) {
		userDetailsDAO.updateUserDetails(userDetails);
	}
}
