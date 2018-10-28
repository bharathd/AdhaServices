package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Otp;

public interface OtpDAO {
	
	List<Otp> getOtpByPhoneNumber(String phoneNumber);
	void addOrUpdateOtp(Otp otp);

}
