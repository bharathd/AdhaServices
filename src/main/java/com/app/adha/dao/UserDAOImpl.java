package com.app.adha.dao;

import com.app.adha.entity.Otp;
import com.app.adha.entity.User;
import com.app.adha.util.UtilMethods;

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
	public List<User> getUserByPhoneNumber(String phoneNumber) {
		
		return (List<User>) entityManager.createQuery("from User where phone_number = :phone_number ").setParameter("phone_number", phoneNumber).getResultList();
	}
	
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
	   entityManager.flush();
	}
	
	@Override
	public void deleteUser(int userId) {
		String update_query = "update User set status = :status where user_id = :user_id";
		entityManager.createQuery(update_query).setParameter("status", 3).setParameter("user_id", userId).executeUpdate();
	}
	
	@Override
	public int maxRecordId() {
		String max_id = "SELECT max(u.userId) FROM User u";
		return (int) entityManager.createQuery(max_id).getResultList().get(0);
	}
	

}
