package com.app.adha.controller;

import java.util.List;

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

import com.app.adha.entity.Gift;
import com.app.adha.service.GiftService;

@CrossOrigin(origins = { "http://159.65.145.220:8080" }, maxAge = 3000)
@RestController
@RequestMapping("/gift")
public class GiftController {
	
	@Autowired
	GiftService giftService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Gift> getGiftById(@PathVariable("id") int id) {
        System.out.println("Fetching Photo with id " + id);
        Gift gift = giftService.getGiftById(id);
        if (gift == null) {
            return new ResponseEntity<Gift>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Gift>(gift, HttpStatus.OK);
    }

    @GetMapping("gifts")
	public ResponseEntity<List<Gift>> getAllGifts() {
		List<Gift> list = giftService.getAllGifts();
		return new ResponseEntity<List<Gift>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addgift", headers="Accept=application/json")
	public ResponseEntity<Void> addFift(@RequestBody Gift gift, UriComponentsBuilder ucBuilder) {
    	       giftService.addGift(gift);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(gift.getGiftId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/deletegift/{id}")
	public ResponseEntity<Void> deletePhoto(@PathVariable("id") Integer id) {
    	giftService.deleteGift(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

}
