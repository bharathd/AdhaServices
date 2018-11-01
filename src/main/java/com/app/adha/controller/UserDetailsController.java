package com.app.adha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.User;
import com.app.adha.entity.UserDetails;
import com.app.adha.service.UserDetailsService;

@CrossOrigin(origins = { "http://159.65.145.220:8080" }, maxAge = 3000)
@RestController
@RequestMapping("/userdetails")
public class UserDetailsController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDetails>> getUserDetailsById(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        List<UserDetails> user_details = userDetailsService.getUserDetailsById(id);
        if (user_details == null) {
            return new ResponseEntity<List<UserDetails>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UserDetails>>(user_details, HttpStatus.OK);
    }
	
	@PostMapping(value="/adduserdetails", headers="Accept=application/json")
	public ResponseEntity<Void> addUserDetails(@RequestBody UserDetails userDetails, UriComponentsBuilder ucBuilder) {
		       userDetailsService.addUserDetails(userDetails);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/userdetails/{id}").buildAndExpand(userDetails.getUserId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	@PutMapping("/updateuserdetails")
	public ResponseEntity<UserDetails> updateUserDetails(@RequestBody UserDetails userDetails) {
		userDetailsService.updateUserDetails(userDetails);
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}

}
