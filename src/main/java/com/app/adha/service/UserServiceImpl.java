package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.UserDAO;
import com.app.adha.entity.User;
import com.app.adha.util.UtilMethods;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UtilMethods utilMethod;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date dateobj = new Date();
	
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
		//this is for username field value
		int maxUserId = maxRecordId()+1;
		
		if(user.getUserRoll() == 0) {
			user.setUserRoll(UtilMethods.MODEL);
			user.setStatus(UtilMethods.VERIFY);
			user.setTerms(UtilMethods.NO);
			user.setUserName("USER-" + maxUserId);
		}else if(user.getUserRoll() == UtilMethods.CUSTOMER){
			user.setUserName("CUS-" + maxUserId);
			user.setStatus(UtilMethods.ACTIVE);
			user.setTerms(UtilMethods.YES);
		}else if(user.getUserRoll() == UtilMethods.ADMIN) {
			user.setUserName("ADM-" + maxUserId);
			user.setStatus(UtilMethods.ACTIVE);
			user.setTerms(UtilMethods.YES);
		}else if(user.getUserRoll() == UtilMethods.ADMIN) {
			user.setUserName("SUADM-" + maxUserId);
			user.setStatus(UtilMethods.ACTIVE);
			user.setTerms(UtilMethods.YES);
		}
		user.setCreatedDate(df.format(dateobj));
		
        userDAO.addUser(user);
    }
	
	@Override
	public void updateUser(User user) {
		User usr = getUserById(user.getUserId());
		usr.setMailId(user.getMailId());
		usr.setPhoneNumber(user.getPhoneNumber());
		usr.setTerms(user.getTerms());
		usr.setStatus(user.getStatus());
		userDAO.updateUser(usr);
	}
	
	@Override
	public void deleteUser(int userId) {
		userDAO.deleteUser(userId);
	}
	
	@Override
	public int maxRecordId() {
		return userDAO.maxRecordId();
	}
	
	
	
}
