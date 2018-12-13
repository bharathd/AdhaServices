package com.app.adha.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    
    @Override
    public List<ServiceDetails> getScheduleServiceDetails(int userId, String scheduleDate){
    	Date date1 = new Date();
    	try {
			date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scheduleDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
    	String start_date =df.format(date1);
		String end_date = df.format(date1);
		Calendar cal = Calendar.getInstance();
		try {
		cal.setTime(df.parse(start_date));
        cal.add(Calendar.DATE, 0); 
        cal.set(Calendar.HOUR_OF_DAY, 0); 
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0); 
        start_date = df.format(cal.getTime());
        cal.setTime(df.parse(end_date));
        cal.add(Calendar.DATE, 1); 
        cal.set(Calendar.HOUR_OF_DAY, 23); 
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59); 
        end_date = df.format(cal.getTime());	
		}catch(Exception e) {
			e.printStackTrace();
		}
    	return serviceDetailsDAO.getScheduleServiceDetails(userId, start_date, end_date);
    }

}
