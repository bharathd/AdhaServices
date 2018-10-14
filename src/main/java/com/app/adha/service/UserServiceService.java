package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.UserService;

public interface UserServiceService {
	
	List<UserService> getAllServices();
	UserService getServiceById(int serviceId);
    void addService(UserService service);

}
