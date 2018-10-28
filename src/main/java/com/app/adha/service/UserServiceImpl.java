package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.service.OtpService;
import com.app.adha.dao.UserDAO;
import com.app.adha.entity.Otp;
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
	
	@Autowired
    private OtpService otpService;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
	
    @Override
	public List<User> getUserByPhoneNumber(String phoneNumber) {
		List<User> user = userDAO.getUserByPhoneNumber(phoneNumber);
		return user;
	}	
    
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
	public String addUser(User user){
		
		List<User> list_user = userDAO.getUserByPhoneNumber(user.getPhoneNumber());
		
		if(list_user.size()>0) {
			otpService.addOrUpdateOtp(user.getPhoneNumber());
			return "Account Already exist!";
		}else {
		//this is for username field valu
		int maxUserId = maxRecordId()+1;
		
		if(user.getRole() == UtilMethods.ROLE_MODEL){
			user.setUserName("MODEL-" + maxUserId);
			user.setStatus(UtilMethods.INPROGRESS);
			user.setTerms(UtilMethods.NO);
		}else if(user.getRole() == UtilMethods.ROLE_CUSTOMER){
			user.setUserName("CUSTOMER-" + maxUserId);
			user.setStatus(UtilMethods.ACTIVE);
			user.setTerms(UtilMethods.YES);
		}else if(user.getRole() == UtilMethods.ROLE_ADMIN) {
			user.setUserName("ADMIN-" + maxUserId);
			user.setStatus(UtilMethods.ACTIVE);
			user.setTerms(UtilMethods.YES);
		}else if(user.getRole() == UtilMethods.ROLE_SUPERADMIN) {
			user.setUserName("SUPERADMIN-" + maxUserId);
			user.setStatus(UtilMethods.ACTIVE);
			user.setTerms(UtilMethods.YES);
		}
		user.setCreatedDate(df.format(dateobj));
		
		
        userDAO.addUser(user);
        otpService.addOrUpdateOtp(user.getPhoneNumber());
        return "Account created";
		}
    }
	
	@Override
	public void updateUser(User user) {
		User usr = getUserById(user.getUserId());
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
