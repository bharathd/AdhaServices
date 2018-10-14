package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Plan;

@Transactional
@Repository
public class PlanDAOImpl implements PlanDAO{

	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public Plan getPlanById(int planId) {
		return entityManager.find(Plan.class, planId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Plan> getAllPlans() {
		String hql = "FROM Otp as o ORDER BY o.userId";
		return (List<Plan>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addPlan(Plan plan) {
		entityManager.persist(plan);
	}

}
