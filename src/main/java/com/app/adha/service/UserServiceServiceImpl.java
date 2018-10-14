package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.UserServiceDAO;
import com.app.adha.entity.UserService;

@Service
public class UserServiceServiceImpl implements UserServiceService{
	
	@Autowired
	private UserServiceDAO serviceDAO;
	
	@Override
	public UserService getServiceById(int schedulerId) {
		UserService scheduler = serviceDAO.getServiceById(schedulerId);
		return scheduler;
	}	
	
	@Override
	public List<UserService> getAllServices(){
		return serviceDAO.getAllServices();
	}
	
	@Override
	public void addService(UserService service){
		serviceDAO.addService(service);
	}

}
