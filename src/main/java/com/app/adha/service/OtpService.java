package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Otp;

public interface OtpService {
	
	List<Otp> getAllOtps();
    Otp getOtpById(int userId);
    void addOtp(Otp otp);

}
