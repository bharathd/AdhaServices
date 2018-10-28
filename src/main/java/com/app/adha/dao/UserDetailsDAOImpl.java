package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.User;
import com.app.adha.entity.UserDetails;

@Transactional
@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public void addUserDetails(UserDetails userDetails) {
		entityManager.persist(userDetails);
	}
	
	
	@Override
	public List<UserDetails> getUserDetailsById(int userId) {
		return (List<UserDetails>) entityManager.createQuery("from UserDetails where user_id = :user_id ").setParameter("user_id", userId).getResultList();
	}
	
	@Override
	public void updateUserDetails(UserDetails user) {
		entityManager.flush();
	}

}
