package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Otp;

public interface OtpDAO {
	
	List<Otp> getAllOtps();
    Otp getOtpById(int userId);
    void addOtp(Otp otp);

}
