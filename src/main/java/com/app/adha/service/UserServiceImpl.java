package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.service.OtpService;
import com.app.adha.dao.UserDAO;
import com.app.adha.dao.UserDetailsDAO;
import com.app.adha.entity.Otp;
import com.app.adha.entity.ServiceDetails;
import com.app.adha.entity.User;
import com.app.adha.entity.UserDetails;
import com.app.adha.util.UtilMethods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserDetailsDAO userDetailsDAO;
	
	@Autowired
    private OtpService otpService;
	
	@Autowired
    private NotificationService notificationService;
	
	@Autowired
	ServiceDetailsService serviceDetailsService;
	
	@Autowired
	UserServiceService userServiceService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
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
		obj.setUserDetails(userDetailsService.getUserDetailsById(userId));
		return obj;
	}	
	
	@Override
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	
	@Override
	public String addUser(User user){
		StringBuffer service_details= new StringBuffer();
		List<User> list_user = userDAO.getUserByPhoneNumber(user.getPhoneNumber());
		
		if(list_user.size()>0) {
			otpService.addOrUpdateOtp(user.getPhoneNumber());
			return "Account Already exist!";
		}else {
		//this is for username field value
		int maxUserId = maxRecordId()+1;
		
		if(user.getRole() == UtilMethods.ROLE_MODEL){
			user.setUserName("MODEL-" + maxUserId);
			user.setStatus(UtilMethods.ACCOUNT_INPROGRESS);
			user.setTerms(UtilMethods.NO);
		}else if(user.getRole() == UtilMethods.ROLE_CUSTOMER){
			user.setUserName("CUSTOMER-" + maxUserId);
			user.setStatus(UtilMethods.ACCOUNT_ACTIVE);
			user.setTerms(UtilMethods.YES);
		}else if(user.getRole() == UtilMethods.ROLE_ADMIN && user.getCreatedBy()>0) {
			user.setUserName("ADMIN-" + maxUserId);
			user.setStatus(UtilMethods.ACCOUNT_ACTIVE);
			user.setTerms(UtilMethods.YES);
		}else if(user.getRole() == UtilMethods.ROLE_SUPERADMIN && user.getCreatedBy()>0) {
			user.setUserName("SUPERADMIN-" + maxUserId);
			user.setStatus(UtilMethods.ACCOUNT_ACTIVE);
			user.setTerms(UtilMethods.YES);
		}
		if((user.getRole() == UtilMethods.ROLE_MODEL) || (user.getRole() == UtilMethods.ROLE_CUSTOMER) || (user.getRole() == UtilMethods.ROLE_ADMIN && user.getCreatedBy()>0) || (user.getRole() == UtilMethods.ROLE_SUPERADMIN && user.getCreatedBy()>0)) {
		user.setCreatedDate(df.format(dateobj));
		
		
        userDAO.addUser(user);
        otpService.addOrUpdateOtp(user.getPhoneNumber());
        String description = "Admin added your Account";
        notificationService.addNotification(user.getCreatedBy(), maxUserId, description);
        
        List<com.app.adha.entity.UserService> list_userservices = userServiceService.getAllServices();
        for(com.app.adha.entity.UserService userService : list_userservices) {
        	service_details.append(userService.getServiceName()+"-"+UtilMethods.YES+",");
        }
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setUserId(user.getUserId());
        serviceDetails.setServices(service_details.toString());
        serviceDetailsService.addORUpdateServiceDetails(serviceDetails);
        return "Account created";
		}
		return "Please Contact SuperAdmin";
		}
    }
	
	@Override
	public User updateUser(User user) {
		User usr = getUserById(user.getUserId());
		usr.setStatus(user.getStatus());
		usr.setTerms(user.getTerms());
		userDAO.updateUser(usr);
		String description = "Upddated your account details";
        notificationService.addNotification(usr.getCreatedBy(), usr.getUserId(), description);

		return usr;
	}
	
	@Override
	public void deleteUser(int userId) {
		userDAO.deleteUser(userId);
		User usr = getUserById(userId);
		String description = "Deleted your account";
        notificationService.addNotification(usr.getCreatedBy(), usr.getUserId(), description);
	}
	
	@Override
	public int maxRecordId() {
		return userDAO.maxRecordId();
	}
	
	
	
}
