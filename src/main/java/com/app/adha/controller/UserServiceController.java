package com.app.adha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.UserService;
import com.app.adha.service.UserServiceService;

@RestController
@RequestMapping("/service")
public class UserServiceController {
	
	@Autowired
	UserServiceService userServiceService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserService> getServiceById(@PathVariable("id") int serviceId) {
        System.out.println("Fetching Photo with id " + serviceId);
        UserService photo = userServiceService.getServiceById(serviceId);
        if (photo == null) {
            return new ResponseEntity<UserService>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserService>(photo, HttpStatus.OK);
    }

    @GetMapping("services")
	public ResponseEntity<List<UserService>> getAllServices() {
		List<UserService> list = userServiceService.getAllServices();
		return new ResponseEntity<List<UserService>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addservice", headers="Accept=application/json")
	public ResponseEntity<Void> addPhoto(@RequestBody UserService service, UriComponentsBuilder ucBuilder) {
    	       userServiceService.addService(service);;
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/service/{id}").buildAndExpand(service.getServiceId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
