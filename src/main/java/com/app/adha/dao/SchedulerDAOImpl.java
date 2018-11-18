package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Scheduler;
import com.app.adha.entity.UserService;
import com.app.adha.util.UtilMethods;

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
		String hql = "FROM Scheduler as s ORDER BY s.schedulerId";
		return (List<Scheduler>) entityManager.createQuery(hql).getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Scheduler> getSchedulersByDate(String start_date, String end_date) {
		String hql = "FROM Scheduler where (startTime BETWEEN '"+ start_date + "' AND '" + end_date + "')";
		return (List<Scheduler>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addScheduler(Scheduler scheduler) {
		entityManager.persist(scheduler);
	}
	
	@Override
	public void updateScheduler(Scheduler scheduler) {
	   entityManager.flush();
	}
	
	@Override
	public void deleteScheduler(int schedulerId) {
		String update_query = "update Scheduler set status = :status where id = :id";
		entityManager.createQuery(update_query).setParameter("status", UtilMethods.DELETE).setParameter("id", schedulerId).executeUpdate();
	}

}
