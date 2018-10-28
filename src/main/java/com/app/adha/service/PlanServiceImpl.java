package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.PlanDAO;
import com.app.adha.entity.Plan;

@Service
public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanDAO planDAO;
	
	@Override
	public Plan getPlanById(int planId) {
		Plan plan = planDAO.getPlanById(planId);
		return plan;
	}	
	
	@Override
	public List<Plan> getAllPlans(){
		return planDAO.getAllPlans();
	}
	
	@Override
	public void addPlan(Plan plan){
		planDAO.addPlan(plan);
	}

	@Override
	public void deletePlan(int planId) {
		planDAO.deletePlan(planId);
	}
}
