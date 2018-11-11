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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.City;
import com.app.adha.service.CityService;

@CrossOrigin
@RestController
@RequestMapping("/city")
public class CityController {
	
	private static final Logger logger = LoggerFactory.getLogger(CityController.class);
	
	@Autowired
    CityService cityService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> getCityById(@PathVariable("id") int id) {
		logger.info("Fetching City with id " + id);
        City city = cityService.getCityById(id);
        if (city == null) {
            return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<City>(city, HttpStatus.OK);
    }

    @GetMapping("citys")
	public ResponseEntity<List<City>> getAllCitys() {
		List<City> list = cityService.getAllCitys();
		logger.info("Fetching All Citys " + list);
		return new ResponseEntity<List<City>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addcity", headers="Accept=application/json")
	public ResponseEntity<Void> addCity(@RequestBody City city, UriComponentsBuilder ucBuilder) {
    	       cityService.addCity(city);
    	       logger.info("adding city to database " + city);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(city.getCityId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/deletecity/{id}")
	public ResponseEntity<Void> deleteCity(@PathVariable("id") Integer id) {
    	cityService.deleteCity(id);
    	logger.info("deleted city from database " + id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
