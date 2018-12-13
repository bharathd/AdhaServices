package com.app.adha.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_details")
public class ServiceDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int serviceDetailsId;
    
    @Column(name="user_id")
	private int userId;
    
    @Column(name="services")
	private String services;
    
    @Column(name="created_date")
    private String createdDate;
    
    @Column(name="schedule_services")
	private String scheduleServices;
    
    @Column(name="schedule_date")
    private String scheduleDate;

	public int getServiceDetailsId() {
		return serviceDetailsId;
	}

	public void setServiceDetailsId(int serviceDetailsId) {
		this.serviceDetailsId = serviceDetailsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getScheduleServices() {
		return scheduleServices;
	}

	public void setScheduleServices(String scheduleServices) {
		this.scheduleServices = scheduleServices;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
    
    

}
