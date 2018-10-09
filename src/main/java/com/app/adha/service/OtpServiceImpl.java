package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.OtpDAO;
import com.app.adha.entity.Otp;

@Service
public class OtpServiceImpl implements OtpService{
	
	@Autowired
	private OtpDAO otpDAO;
	
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
		otpDAO.addOtp(otp);
    }

}
