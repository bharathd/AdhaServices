package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Plan;
import com.app.adha.util.UtilMethods;

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
		String hql = "FROM Plan as p ORDER BY p.planId";
		return (List<Plan>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addPlan(Plan plan) {
		entityManager.persist(plan);
	}
	
	@Override
	public void deletePlan(int planId) {
		String update_query = "update Plan set status = :status where id = :id";
		entityManager.createQuery(update_query).setParameter("status", UtilMethods.DELETE).setParameter("id", planId).executeUpdate();
	}

}
