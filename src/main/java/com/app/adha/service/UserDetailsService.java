package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.UserDetails;

public interface UserDetailsService {
	
	void addUserDetails(UserDetails userDetails);
	UserDetails getUserDetailsById(int userId);
    void updateUserDetails(UserDetails user);

}
