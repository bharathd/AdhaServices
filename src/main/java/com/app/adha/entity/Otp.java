package com.app.adha.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp")
public class Otp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="otp_id")
    private int otpId;
	
	@Column(name="user_id")
    private int userId;
	
	@Column(name="mail_id")
    private String mailId;
	
	@Column(name="phone_number")
    private String phoneNumber;
	
	@Column(name="otp_num")
    private String otpNumber;
	
	@Column(name="created_date")
    private Date createdDate;

	public int getOtpId() {
		return otpId;
	}

	public void setOtpId(int otpId) {
		this.otpId = otpId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOtpNumber() {
		return otpNumber;
	}

	public void setOtpNumber(String otpNumber) {
		this.otpNumber = otpNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
