package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.UserDAO;
import com.app.adha.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User getUserById(int userId) {
		User obj = userDAO.getUserById(userId);
		return obj;
	}	
	
	@Override
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	
	@Override
	public void addUser(User user){
    userDAO.addUser(user);
    }
	
	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
	
	@Override
	public void deleteUser(int userId) {
		userDAO.deleteUser(userId);
	}
	
}
