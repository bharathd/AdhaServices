package com.app.adha.dao;

import java.util.List;


import com.app.adha.entity.UserService;

public interface UserServiceDAO {

	List<UserService> getAllServices();
	UserService getServiceById(int serviceId);
    void addService(UserService service);
    void updateService(UserService service);
    void deleteService(int serviceId);
}
