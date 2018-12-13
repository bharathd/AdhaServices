package com.app.adha.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Scheduler;
import com.app.adha.entity.ServiceDetails;

@Transactional
@Repository
public class ServiceDetailsDAOImpl implements ServiceDetailsDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<ServiceDetails> getServiceDetailsByUserId(int userId) {
		return (List<ServiceDetails>) entityManager.createQuery("from ServiceDetails where user_id = :user_id ").setParameter("user_id", userId).getResultList();
	}
	
	@Override
	public void addORUpdateServiceDetails(ServiceDetails serviceDetails) {
		List<ServiceDetails> list_serviceDetails = getServiceDetailsByUserId(serviceDetails.getUserId());
		if(list_serviceDetails.size()>0) {
			String update_query = "update ServiceDetails set services = :services, created_date = :created_date, schedule_services = :schedule_services, schedule_date = :schedule_date where user_id = :user_id";
		    entityManager.createQuery(update_query).setParameter("services", serviceDetails.getServices()).setParameter("created_date", serviceDetails.getCreatedDate()).setParameter("schedule_services", serviceDetails.getScheduleServices()).setParameter("schedule_date", serviceDetails.getScheduleDate()).setParameter("user_id", serviceDetails.getUserId()).executeUpdate();
		}else {
			entityManager.persist(serviceDetails);
	}}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceDetails> getScheduleServiceDetails(int userId, String startDate, String endDate) {
		String hql = "FROM ServiceDetails where userId = '" + userId + "' AND (scheduleDate BETWEEN '"+ startDate + "' AND '" + endDate + "')";
		return (List<ServiceDetails>) entityManager.createQuery(hql).getResultList();
	}

}
