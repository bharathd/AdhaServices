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
	
	@GetMapping(value = "/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Otp>> getOtpById(@PathVariable("phoneNumber") String phoneNumber) {
        System.out.println("Fetching OTP with id " + phoneNumber);
        List<Otp> otp = otpService.getOtpByPhoneNumber(phoneNumber);
        if (otp.size() == 0) {
            return new ResponseEntity<List<Otp>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Otp>>(otp, HttpStatus.OK);
    }

    
    
    @PostMapping(value="/genrateotp", headers="Accept=application/json")
	public ResponseEntity<Void> addOrUpdateOtp(@RequestBody String phoneNumber, UriComponentsBuilder ucBuilder) {
    	       otpService.addOrUpdateOtp(phoneNumber);
               HttpHeaders headers = new HttpHeaders();
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
}
