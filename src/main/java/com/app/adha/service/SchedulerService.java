package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Scheduler;

public interface SchedulerService {
	
	List<Scheduler> getAllSchedulers();
	List<Scheduler> getSchedulersByDate(int date);
	Scheduler getSchedulerById(int schedulerId);
    void addScheduler(Scheduler scheduler);

}
