package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Scheduler;
import com.app.adha.entity.UserService;

public interface SchedulerDAO {
	
	List<Scheduler> getAllSchedulers();
	List<Scheduler> getSchedulersByDate(int userId, String start_date, String end_date, int date);
	Scheduler getSchedulerById(int schedulerId);
    void addScheduler(Scheduler scheduler);
    void updateScheduler(Scheduler scheduler);
    void deleteScheduler(int schedulerId);
    List<Scheduler> getAllSchedulersByUserId(int userId);

}
