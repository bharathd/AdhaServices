package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
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
	public void addScheduler(Scheduler scheduler){
		scheduler.setCreatedDate(df.format(dateobj));
		schedulerDAO.addScheduler(scheduler);
	}


}
