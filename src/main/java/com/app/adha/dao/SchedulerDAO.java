package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Scheduler;

public interface SchedulerDAO {
	
	List<Scheduler> getAllSchedulers();
	List<Scheduler> getSchedulersByDate(String start_date, String end_date);
	Scheduler getSchedulerById(int schedulerId);
    void addScheduler(Scheduler scheduler);

}
