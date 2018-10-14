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

import com.app.adha.entity.Plan;
import com.app.adha.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	PlanService planService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Plan> getPlanById(@PathVariable("id") int id) {
        System.out.println("Fetching Plan with id " + id);
        Plan plan = planService.getPlanById(id);
        if (plan == null) {
            return new ResponseEntity<Plan>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Plan>(plan, HttpStatus.OK);
    }

    @GetMapping("plans")
	public ResponseEntity<List<Plan>> getAllPlans() {
		List<Plan> list = planService.getAllPlans();
		return new ResponseEntity<List<Plan>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addplan", headers="Accept=application/json")
	public ResponseEntity<Void> addPlan(@RequestBody Plan plan, UriComponentsBuilder ucBuilder) {
    	       planService.addPlan(plan);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(plan.getPlanId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
