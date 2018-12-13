package com.app.adha.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int userDetailsId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="updated_date")
	private String updatedDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="logged_type")
	private String loggedType;
	
	@Column(name="city")
	private int city;
	
	@Column(name = "height")
	private float height;
	
	@Column(name = "weight")
	private float weight;
	
	@Column(name="mail_id")
    private String mailId;
	
	@Column(name="insta_id")
    private String instaId;
	
	@Column(name="insta_follower")
    private String instaFollower;
	
	@Column(name="dob")
    private String dateOfBirth;
	
    public int getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(int userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getLoggedType() {
		return loggedType;
	}

	public void setLoggedType(String loggedType) {
		this.loggedType = loggedType;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	
    public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	
    public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getInstaId() {
		return instaId;
	}

	public void setInstaId(String instaId) {
		this.instaId = instaId;
	}

	public String getInstaFollower() {
		return instaFollower;
	}

	public void setInstaFollower(String instaFollower) {
		this.instaFollower = instaFollower;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

   
	
}
