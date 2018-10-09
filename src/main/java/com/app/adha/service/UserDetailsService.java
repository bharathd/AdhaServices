package com.app.adha.service;

import com.app.adha.entity.UserDetails;

public interface UserDetailsService {
	
	UserDetails getUserDetailsById(int userId);
    void updateUserDetails(UserDetails user);

}
