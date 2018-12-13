package com.app.adha.controller;

import com.app.adha.exception.ResourceNotFoundException;
import com.app.adha.entity.User;
import com.app.adha.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Anil on 03/10/18.
 */

@CrossOrigin(origins = "*", maxAge = 4800, allowCredentials = "false", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
    	logger.info("Fetching User with id " + id);
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    
    @GetMapping("users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		logger.info("Fetching All Users " + list);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
    
    @GetMapping("users/{userRoll}")
	public ResponseEntity<List<User>> getAllUsersByUserRole(@PathVariable("userRoll") int userRole) {
		List<User> list = userService.getAllUsersByUserRole(userRole);
		logger.info("Fetching All Users " + list);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/login")
	public ResponseEntity<List<User>> addUser(@RequestBody User user) {
    	
    	    if(user.getPhoneNumber().equals("")|| user.getPhoneNumber().equals(null)) {
    	    	return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
    	    }else {
    	    String message =  userService.addUser(user);
    	    logger.info("Adding Users " + user);
    	    List<User> user_info = userService.getUserByPhoneNumber(user.getPhoneNumber());
    	    if(message.equals("Account created")) 
    	      return new ResponseEntity<List<User>>(user_info, HttpStatus.CREATED);
    	    else if(message.equals("Please Contact SuperAdmin"))
    	    	return new ResponseEntity<List<User>>(user_info, HttpStatus.FORBIDDEN);
    	    else 
    	      return new ResponseEntity<List<User>>(user_info,HttpStatus.OK);
    	    }
    	    	
    	  
    	    
    }
    
    @PostMapping(value="/createuser")
	public ResponseEntity<List<User>> createUser(@RequestBody User user) {
    	
    	    if(user.getPhoneNumber().equals("")|| user.getPhoneNumber().equals(null)) {
    	    	return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
    	    }else {
    	    String message =  userService.createUser(user);
    	    logger.info("Adding Users " + user);
    	    List<User> user_info = userService.getUserByPhoneNumber(user.getPhoneNumber());
    	    if(message.equals("Account created")) 
    	      return new ResponseEntity<List<User>>(user_info, HttpStatus.CREATED);
    	    else if(message.equals("Please Contact SuperAdmin"))
    	    	return new ResponseEntity<List<User>>(user_info, HttpStatus.FORBIDDEN);
    	    else 
    	      return new ResponseEntity<List<User>>(user_info,HttpStatus.OK);
    	    }
    	    	
    	  
    	    
    }
    
    
	@PutMapping(value="/updateuser", headers="Accept=application/json")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User update_user = userService.updateUser(user);
		logger.info("Updating User " + update_user);
		return new ResponseEntity<User>(update_user, HttpStatus.OK);
	}
	
    
	@DeleteMapping(value="/deleteuser/{id}", headers="Accept=application/json")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		logger.info("Deleting  User " + id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

    
}
