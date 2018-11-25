package com.app.adha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.app.adha.entity.UserService;
import com.app.adha.entity.ServiceDetails;
import com.app.adha.service.UserServiceService;
import com.app.adha.service.ServiceDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/service")
public class UserServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
	
	@Autowired
	UserServiceService userServiceService;
	
	@Autowired
	ServiceDetailsService serviceDetailsService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserService> getServiceById(@PathVariable("id") int serviceId) {
		logger.info("Fetching Service with id " + serviceId);
        UserService photo = userServiceService.getServiceById(serviceId);
        if (photo == null) {
            return new ResponseEntity<UserService>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserService>(photo, HttpStatus.OK);
    }

    @GetMapping("servicedetails/{userid}")
	public ResponseEntity<List<ServiceDetails>> getServiceDetailsByUserId(@PathVariable("userid") int userId) {
		List<ServiceDetails> list = serviceDetailsService.getServiceDetailsByUserId(userId);
		logger.info("Fetching ServiceDetails by userId " + list);
		return new ResponseEntity<List<ServiceDetails>>(list, HttpStatus.OK);
	}
    
    @GetMapping("services")
	public ResponseEntity<List<UserService>> getAllServices() {
		List<UserService> list = userServiceService.getAllServices();
		logger.info("Fetching All Services" + list);
		return new ResponseEntity<List<UserService>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addservice", headers="Accept=application/json")
	public ResponseEntity<Void> addService(@RequestBody UserService service, UriComponentsBuilder ucBuilder) {
    	       userServiceService.addService(service);
    	       logger.info("Add Service" + service);
               HttpHeaders headers = new HttpHeaders();
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @PostMapping(value="/addservicedetails", headers="Accept=application/json")
	public ResponseEntity<Void> addService(@RequestBody ServiceDetails serviceDetails, UriComponentsBuilder ucBuilder) {
    	       serviceDetailsService.addORUpdateServiceDetails(serviceDetails);
    	       logger.info("Add ServiceDetails" + serviceDetails);
               HttpHeaders headers = new HttpHeaders();
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping("/updateservice")
	public ResponseEntity<UserService> updateService(@RequestBody UserService service) {
    	UserService update_userService = userServiceService.updateService(service);
		logger.info("Updating UserService " + update_userService);
		return new ResponseEntity<UserService>(update_userService, HttpStatus.OK);
	}
	
    
	@DeleteMapping("/deleteservice/{id}")
	public ResponseEntity<Void> deleteService(@PathVariable("id") Integer serviceId) {
		userServiceService.deleteService(serviceId);
		logger.info("Deleting  UserService " + serviceId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
