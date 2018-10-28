package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.UserDetails;

public interface UserDetailsDAO {
	
	void addUserDetails(UserDetails userDetails);
	List<UserDetails> getUserDetailsById(int userId);
    void updateUserDetails(UserDetails user);
}
