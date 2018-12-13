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
@Table(name = "gift")
public class Gift implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gift_id")
    private int giftId;
    
    @Column(name="name")
	private String giftName;
    
    @Column(name="created_by")
    private int createdBy;
	
	@Column(name="created_date")
    private String createdDate;
	
	@Column(name="status")
    private int status;
	
	@Column(name="amount")
	private String amount;

	public int getGiftId() {
		return giftId;
	}

	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	

}
