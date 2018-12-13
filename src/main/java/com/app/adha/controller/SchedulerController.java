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

import com.app.adha.entity.Scheduler;
import com.app.adha.entity.UserService;
import com.app.adha.service.SchedulerService;

//@CrossOrigin
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerController.class);
	
	@Autowired
	SchedulerService schedulerService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Scheduler> getSchedulerById(@PathVariable("id") int id) {
		logger.info("Fetching Scheduler with id " + id);
        Scheduler scheduler = schedulerService.getSchedulerById(id);
        if (scheduler == null) {
            return new ResponseEntity<Scheduler>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Scheduler>(scheduler, HttpStatus.OK);
    }

    @GetMapping("schedulers/{userid}/{date}")
	public ResponseEntity<List<Scheduler>> getSchedulersByDate(@PathVariable("userid") int userId, @PathVariable("date") int date) {
		List<Scheduler> list = schedulerService.getSchedulersByDate(userId, date);
		logger.info("Fetching Schedulers with date " + userId + " " + date);
		return new ResponseEntity<List<Scheduler>>(list, HttpStatus.OK);
	}
    
    
    @GetMapping("schedulers/{userid}")
	public ResponseEntity<List<Scheduler>> getAllSchedulersByUserId(@PathVariable("userid") int userId) {
		List<Scheduler> list = schedulerService.getAllSchedulersByUserId(userId);
		logger.info("Fetching All Schedulers" + list);
		return new ResponseEntity<List<Scheduler>>(list, HttpStatus.OK);
	}
    
    @GetMapping("schedulers")
	public ResponseEntity<List<Scheduler>> getAllSchedulers() {
		List<Scheduler> list = schedulerService.getAllSchedulers();
		logger.info("Fetching All Schedulers" + list);
		return new ResponseEntity<List<Scheduler>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addscheduler", headers="Accept=application/json")
	public ResponseEntity<Void> addScheduler(@RequestBody Scheduler scheduler, UriComponentsBuilder ucBuilder) {
    	       schedulerService.addScheduler(scheduler);
    	       logger.info("Adding Scheduler" + scheduler);
               HttpHeaders headers = new HttpHeaders();
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping("/updatescheduler")
	public ResponseEntity<Scheduler> updateScheduler(@RequestBody Scheduler scheduler) {
    	Scheduler update_scheduler = schedulerService.updateScheduler(scheduler);;
		logger.info("Updating Scheduler " + update_scheduler);
		return new ResponseEntity<Scheduler>(update_scheduler, HttpStatus.OK);
	}
	
    
	@DeleteMapping("/deletescheduler/{id}")
	public ResponseEntity<Void> deleteScheduler(@PathVariable("id") Integer schedulerId) {
		schedulerService.deleteScheduler(schedulerId);
		logger.info("Deleting  Scheduler " + schedulerId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
