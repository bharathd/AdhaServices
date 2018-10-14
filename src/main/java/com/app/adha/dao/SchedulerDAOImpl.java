package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Scheduler;

@Transactional
@Repository
public class SchedulerDAOImpl implements SchedulerDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public Scheduler getSchedulerById(int schedulerId) {
		return entityManager.find(Scheduler.class, schedulerId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Scheduler> getAllSchedulers() {
		String hql = "FROM Otp as o ORDER BY o.userId";
		return (List<Scheduler>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addScheduler(Scheduler scheduler) {
		entityManager.persist(scheduler);
	}

}
