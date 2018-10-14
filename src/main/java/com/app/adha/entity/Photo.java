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
@Table(name = "photos")
public class Photo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int photoId;
	
	@Column(name="user_id")
    private int userId;
	
	@Column(name="url")
    private String photoURL;
	
	@Column(name="uploded_by")
    private int uplodedBy;
	
	@Column(name="uploded_date")
    private Date uplodedDate;

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public int getUplodedBy() {
		return uplodedBy;
	}

	public void setUplodedBy(int uplodedBy) {
		this.uplodedBy = uplodedBy;
	}

	public Date getUplodedDate() {
		return uplodedDate;
	}

	public void setUplodedDate(Date uplodedDate) {
		this.uplodedDate = uplodedDate;
	}
	
	

}
