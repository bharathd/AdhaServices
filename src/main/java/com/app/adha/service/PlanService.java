package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Plan;

public interface PlanService {
	
	List<Plan> getAllPlans();
	Plan getPlanById(int planId);
    void addPlan(Plan plan);

}
