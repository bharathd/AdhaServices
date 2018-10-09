package com.app.adha.dao;



import com.app.adha.entity.UserDetails;

public interface UserDetailsDAO {
	
	UserDetails getUserDetailsById(int userId);
    void updateUserDetails(UserDetails user);
}
