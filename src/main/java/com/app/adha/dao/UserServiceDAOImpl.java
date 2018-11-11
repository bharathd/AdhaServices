package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.User;
import com.app.adha.entity.UserService;

@Transactional
@Repository
public class UserServiceDAOImpl implements UserServiceDAO{

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public UserService getServiceById(int serviceId) {
		return entityManager.find(UserService.class, serviceId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserService> getAllServices() {
		String hql = "FROM UserService as s ORDER BY s.serviceId";
		return (List<UserService>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addService(UserService service) {
		entityManager.persist(service);
	}
	
	@Override
	public void updateService(UserService service) {
	   entityManager.flush();
	}
	
	@Override
	public void deleteService(int serviceId) {
		String update_query = "update UserService set status = :status where id = :id";
		entityManager.createQuery(update_query).setParameter("status", 3).setParameter("id", serviceId).executeUpdate();
	}
}
