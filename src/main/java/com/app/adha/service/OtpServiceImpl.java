package com.app.adha.service;

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
	private UtilMethods utilMethod;
	
	@Override
	public Otp getOtpById(int userId) {
		Otp otp = otpDAO.getOtpById(userId);
		return otp;
	}	
	
	@Override
	public List<Otp> getAllOtps(){
		return otpDAO.getAllOtps();
	}
	
	@Override
	public void addOtp(Otp otp){
		
		String to_mail = otp.getMailId();
		
		
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    String otpNumber = String.format("%06d", number);
	    otp.setOtpNumber(otpNumber);
	    otpDAO.addOtp(otp);
	    
	    utilMethod.sendMail(to_mail, otpNumber);
    }

}
