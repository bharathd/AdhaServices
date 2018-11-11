package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.UserServiceDAO;
import com.app.adha.entity.User;
import com.app.adha.entity.UserService;

@Service
public class UserServiceServiceImpl implements UserServiceService{
	
	@Autowired
	private UserServiceDAO serviceDAO;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
	
	@Override
	public UserService getServiceById(int serviceId) {
		UserService scheduler = serviceDAO.getServiceById(serviceId);
		return scheduler;
	}	
	
	@Override
	public List<UserService> getAllServices(){
		return serviceDAO.getAllServices();
	}
	
	@Override
	public void addService(UserService service){
		service.setCreatedDate(df.format(dateobj));
		serviceDAO.addService(service);
	}
	
	@Override
	public void updateService(UserService service) {
		UserService user_service = getServiceById(service.getServiceId());
		serviceDAO.updateService(user_service);
	}
	
	@Override
	public void deleteService(int serviceId) {
		serviceDAO.deleteService(serviceId);
	}

}
