package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Otp;

public interface OtpService {
	
	List<Otp> getOtpByPhoneNumber(String phoneNumber);
    void addOrUpdateOtp(String phoneNumber);

}
