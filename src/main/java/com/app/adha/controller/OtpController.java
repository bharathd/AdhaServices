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

import com.app.adha.entity.Otp;
import com.app.adha.service.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {

	@Autowired
    OtpService otpService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Otp> getOtpById(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Otp otp = otpService.getOtpById(id);
        if (otp == null) {
            return new ResponseEntity<Otp>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Otp>(otp, HttpStatus.OK);
    }

    @GetMapping("otps")
	public ResponseEntity<List<Otp>> getAllOtps() {
		List<Otp> list = otpService.getAllOtps();
		return new ResponseEntity<List<Otp>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addotp", headers="Accept=application/json")
	public ResponseEntity<Void> addUser(@RequestBody Otp otp, UriComponentsBuilder ucBuilder) {
    	       otpService.addOtp(otp);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/otp/{id}").buildAndExpand(otp.getUserId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
}
