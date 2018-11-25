package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.ServiceDetails;

public interface ServiceDetailsDAO {

	List<ServiceDetails> getServiceDetailsByUserId(int userId);
    void addORUpdateServiceDetails(ServiceDetails serviceDetails);
    
}
