package com.app.adha.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.UserDetails;

@Transactional
@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public UserDetails getUserDetailsById(int userId) {
		return entityManager.find(UserDetails.class, userId);
	}
	
	@Override
	public void updateUserDetails(UserDetails user) {
		UserDetails usr_details = getUserDetailsById(user.getUserId());
		usr_details.setFirstName(user.getFirstName());
		usr_details.setLastName(user.getLastName());
		usr_details.setUpdatedDate(user.getUpdatedDate());
		usr_details.setUpdatedBy(user.getUpdatedBy());
		
		entityManager.flush();
	}

}
