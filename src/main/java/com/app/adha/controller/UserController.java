package com.app.adha.controller;

import com.app.adha.exception.ResourceNotFoundException;
import com.app.adha.entity.User;
import com.app.adha.service.UserService;
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

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    
    @GetMapping("users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
    
    
    @PostMapping(value="/login", headers="Accept=application/json")
	public ResponseEntity<List<User>> addUser(@RequestBody User user) {
    	
    	    String message =  userService.addUser(user);
    	    List<User> user_info = userService.getUserByPhoneNumber(user.getPhoneNumber());
    	    if(message.equals("Account created")) 
    	      return new ResponseEntity<List<User>>(user_info, HttpStatus.CREATED);
    	    else 
    	      return new ResponseEntity<List<User>>(user_info,HttpStatus.OK);
    	    
    }
    
    
	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
    
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

    
}
