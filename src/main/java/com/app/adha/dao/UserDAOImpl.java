package com.app.adha.dao;

import com.app.adha.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by Anil on 03/10/18.
 */
@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public User getUserById(int userId) {
		return entityManager.find(User.class, userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User as u ORDER BY u.userId";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}
	
	@Override
	public void updateUser(User user) {
		User usr = getUserById(user.getUserId());
		 usr.setMailId(user.getMailId());
		 usr.setPhoneNumber(user.getPhoneNumber());
		 usr.setTerms(user.getTerms());
		 usr.setStatus(user.getStatus());
		
		entityManager.flush();
	}
	
	@Override
	public void deleteUser(int userId) {
		entityManager.remove(getUserById(userId));
	}
	
	
	

}
