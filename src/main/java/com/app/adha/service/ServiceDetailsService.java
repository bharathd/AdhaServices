package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.ServiceDetails;

public interface ServiceDetailsService {
	
	List<ServiceDetails> getServiceDetailsByUserId(int userId);
    void addORUpdateServiceDetails(ServiceDetails serviceDetails);
    List<ServiceDetails> getScheduleServiceDetails(int userId, String scheduleDate);

}
