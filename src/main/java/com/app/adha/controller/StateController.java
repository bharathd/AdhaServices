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

import com.app.adha.entity.State;
import com.app.adha.service.StateService;

@RestController
@RequestMapping("/state")
public class StateController {
	
	@Autowired
	StateService stateService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<State> getStateById(@PathVariable("id") int id) {
        System.out.println("Fetching State with id " + id);
        State state = stateService.getStateById(id);
        if (state == null) {
            return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<State>(state, HttpStatus.OK);
    }

    @GetMapping("states")
	public ResponseEntity<List<State>> getAllStates() {
		List<State> list = stateService.getAllStates();
		return new ResponseEntity<List<State>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addstate", headers="Accept=application/json")
	public ResponseEntity<Void> addState(@RequestBody State state, UriComponentsBuilder ucBuilder) {
    	       stateService.addState(state);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/state/{id}").buildAndExpand(state.getStateId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
