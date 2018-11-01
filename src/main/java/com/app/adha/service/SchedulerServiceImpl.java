package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.SchedulerDAO;
import com.app.adha.entity.Scheduler;

@Service
public class SchedulerServiceImpl implements SchedulerService{
	
	@Autowired
	private SchedulerDAO schedulerDAO;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date dateobj = new Date();
	
	@Override
	public Scheduler getSchedulerById(int schedulerId) {
		Scheduler scheduler = schedulerDAO.getSchedulerById(schedulerId);
		return scheduler;
	}	
	
	@Override
	public List<Scheduler> getAllSchedulers(){
		return schedulerDAO.getAllSchedulers();
	}
	
	@Override
	public List<Scheduler> getSchedulersByDate(int date){
		String start_date =df.format(dateobj);
		String end_date = df.format(dateobj);
		Calendar cal = Calendar.getInstance();
		
			try {
				if(date==1) {
			       cal.setTime(df.parse(start_date));
	               cal.add(Calendar.DATE, 1); 
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
				}else if(date == 0) {
					cal.setTime(df.parse(start_date));
			        cal.add(Calendar.DATE, 1); 
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
				}else if(date == -1) {
					cal.setTime(df.parse(start_date));
			        cal.add(Calendar.DATE, -1); 
			        cal.set(Calendar.HOUR_OF_DAY, 0); 
			        cal.set(Calendar.MINUTE, 0);
			        cal.set(Calendar.SECOND, 0); 
			        start_date = df.format(cal.getTime());
			        cal.setTime(df.parse(end_date));
			        cal.add(Calendar.DATE, -1); 
			        cal.set(Calendar.HOUR_OF_DAY, 23); 
			        cal.set(Calendar.MINUTE, 59);
			        cal.set(Calendar.SECOND, 59); 
			        end_date = df.format(cal.getTime());	
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return schedulerDAO.getSchedulersByDate(start_date, end_date);
	}
	
	
	
	@Override
	public void addScheduler(Scheduler scheduler){
		scheduler.setCreatedDate(df.format(dateobj));
		schedulerDAO.addScheduler(scheduler);
	}


}
