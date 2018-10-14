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

import com.app.adha.entity.City;
import com.app.adha.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
	
	@Autowired
    CityService cityService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> getCityById(@PathVariable("id") int id) {
        System.out.println("Fetching City with id " + id);
        City city = cityService.getCityById(id);
        if (city == null) {
            return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<City>(city, HttpStatus.OK);
    }

    @GetMapping("citys")
	public ResponseEntity<List<City>> getAllCitys() {
		List<City> list = cityService.getAllCitys();
		return new ResponseEntity<List<City>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/adducity", headers="Accept=application/json")
	public ResponseEntity<Void> addCity(@RequestBody City city, UriComponentsBuilder ucBuilder) {
    	       cityService.addCity(city);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(city.getCityId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
