package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.ServiceDetailsDAO;
import com.app.adha.entity.ServiceDetails;

@Service
public class ServiceDetailsServiceImpl implements ServiceDetailsService{
	
	@Autowired
	public ServiceDetailsDAO serviceDetailsDAO; 
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
    
    @Override
	public List<ServiceDetails> getServiceDetailsByUserId(int userId) {
    	return serviceDetailsDAO.getServiceDetailsByUserId(userId);
		
	}
    
    @Override
	public void addORUpdateServiceDetails(ServiceDetails serviceDetails){
    	serviceDetails.setCreatedDate(df.format(dateobj));
    	//serviceDetails.setServices(serviceDetails.getServices());
    	serviceDetailsDAO.addORUpdateServiceDetails(serviceDetails);
    }

}
