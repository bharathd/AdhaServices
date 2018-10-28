package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.OtpDAO;
import com.app.adha.entity.Otp;
import com.app.adha.util.UtilMethods;

@Service
public class OtpServiceImpl implements OtpService{
	
	@Autowired
	private OtpDAO otpDAO;
	
	@Autowired
	private Otp otp;
	
	@Autowired
	private UtilMethods utilMethod;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
	
	@Override
	public List<Otp> getOtpByPhoneNumber(String phoneNumber) {
		List<Otp> otp = otpDAO.getOtpByPhoneNumber(phoneNumber);
		return otp;
	}	
	
	
	@Override
	public void addOrUpdateOtp(String phoneNumber){
		
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    String otpNumber = String.format("%06d", number);
	    otp.setPhoneNumber(phoneNumber);
	    otp.setOtpNumber(otpNumber);
	    otp.setCreatedDate(df.format(dateobj));
	    otpDAO.addOrUpdateOtp(otp);
	    
	    utilMethod.genrateOTP(phoneNumber, otpNumber);
    }

}
