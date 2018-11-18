package com.app.adha.entity;





import javax.persistence.*;

import java.util.Date;
import java.io.Serializable;

/**
 * Created by Anil on 03/10/18.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Column(name="phone_number")
    private String phoneNumber;
    
    @Column(name="role")
    private int role;
    
    @Column(name="status")
    private int status;
    
    @Column(name="created_date")
    private String createdDate;
    
    @Column(name="created_by")
    private int createdBy;
    
    @Column(name="user_name")
    private String userName;
    
    @Column(name="terms_conditions")
    private int terms;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTerms() {
		return terms;
	}

	public void setTerms(int terms) {
		this.terms = terms;
	}

	

}
