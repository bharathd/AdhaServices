package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Plan;

public interface PlanDAO {

	List<Plan> getAllPlans();
	Plan getPlanById(int planId);
    void addPlan(Plan plan);
    void deletePlan(int planId);
}
