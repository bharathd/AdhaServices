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
import com.app.adha.entity.UserService;
import com.app.adha.util.UtilMethods;

@Service
public class SchedulerServiceImpl implements SchedulerService{
	
	@Autowired
	private SchedulerDAO schedulerDAO;
	
	@Autowired
    private NotificationService notificationService;
	
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
		scheduler.setStatus(UtilMethods.ACTIVE);
		schedulerDAO.addScheduler(scheduler);
		String description ="Customer Requested a Schedule";
		notificationService.addNotification(scheduler.getCustomerId(), scheduler.getModelId(), description);
	}

	@Override
	public Scheduler updateScheduler(Scheduler scheduler) {
		Scheduler user_scheduler = getSchedulerById(scheduler.getServiceId());
		user_scheduler.setAdminId(scheduler.getAdminId());
		user_scheduler.setCreatedBy(scheduler.getCreatedBy());
		user_scheduler.setModelId(scheduler.getModelId());
		user_scheduler.setCustomerId(scheduler.getCustomerId());
		user_scheduler.setStatus(scheduler.getStatus());
		user_scheduler.setStartTime(scheduler.getStartTime());
		user_scheduler.setEndTime(scheduler.getEndTime());
		user_scheduler.setUpdatedDate(df.format(dateobj));
		schedulerDAO.updateScheduler(user_scheduler);
		String description ="Customer Updated a Schedule";
		notificationService.addNotification(user_scheduler.getCustomerId(), user_scheduler.getModelId(), description);
		return user_scheduler;
	}
	
	@Override
	public void deleteScheduler(int schedulerId) {
		schedulerDAO.deleteScheduler(schedulerId);
		Scheduler user_scheduler = getSchedulerById(schedulerId);
		String description ="Customer Delete a Schedule";
		notificationService.addNotification(user_scheduler.getCustomerId(), user_scheduler.getModelId(), description);
	}

}
