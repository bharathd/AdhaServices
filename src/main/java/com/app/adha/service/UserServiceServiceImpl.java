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
import com.app.adha.util.UtilMethods;

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
		service.setStatus(UtilMethods.ACTIVE);
		serviceDAO.addService(service);
	}
	
	@Override
	public UserService updateService(UserService service) {
		UserService user_service = getServiceById(service.getServiceId());
		user_service.setCreatedDate(df.format(dateobj));
		user_service.setCreatedBy(service.getCreatedBy());
		user_service.setServiceName(service.getServiceName());
		user_service.setStatus(service.getStatus());
		user_service.setAmount(service.getAmount());
		serviceDAO.updateService(user_service);
		return user_service;
	}
	
	@Override
	public void deleteService(int serviceId) {
		serviceDAO.deleteService(serviceId);
	}

}
