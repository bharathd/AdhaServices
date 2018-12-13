package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Scheduler;

public interface SchedulerService {
	
	List<Scheduler> getAllSchedulers();
	List<Scheduler> getSchedulersByDate(int userId, int date);
	Scheduler getSchedulerById(int schedulerId);
    void addScheduler(Scheduler scheduler);
    Scheduler updateScheduler(Scheduler scheduler);
    void deleteScheduler(int schedulerId);
    List<Scheduler> getAllSchedulersByUserId(int userId);

}
