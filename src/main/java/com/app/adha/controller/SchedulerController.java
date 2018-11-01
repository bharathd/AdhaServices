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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.Scheduler;
import com.app.adha.service.SchedulerService;

@CrossOrigin(origins = { "http://159.65.145.220:8080" }, maxAge = 3000)
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
	
	@Autowired
	SchedulerService schedulerService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Scheduler> getSchedulerById(@PathVariable("id") int id) {
        System.out.println("Fetching Scheduler with id " + id);
        Scheduler scheduler = schedulerService.getSchedulerById(id);
        if (scheduler == null) {
            return new ResponseEntity<Scheduler>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Scheduler>(scheduler, HttpStatus.OK);
    }

    @GetMapping("schedulers/{date}")
	public ResponseEntity<List<Scheduler>> getSchedulersByDate(@PathVariable("date") int date) {
		List<Scheduler> list = schedulerService.getSchedulersByDate(date);
		return new ResponseEntity<List<Scheduler>>(list, HttpStatus.OK);
	}
    
    @GetMapping("schedulers")
	public ResponseEntity<List<Scheduler>> getAllSchedulers() {
		List<Scheduler> list = schedulerService.getAllSchedulers();
		return new ResponseEntity<List<Scheduler>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addscheduler", headers="Accept=application/json")
	public ResponseEntity<Void> addScheduler(@RequestBody Scheduler scheduler, UriComponentsBuilder ucBuilder) {
    	       schedulerService.addScheduler(scheduler);;
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/scheduler/{id}").buildAndExpand(scheduler.getSchedulerId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
