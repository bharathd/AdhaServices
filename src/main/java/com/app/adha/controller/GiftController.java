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

import com.app.adha.entity.Gift;
import com.app.adha.entity.UserService;
import com.app.adha.service.GiftService;

@CrossOrigin
@RestController
@RequestMapping("/gift")
public class GiftController {
	
	private static final Logger logger = LoggerFactory.getLogger(GiftController.class);
	
	@Autowired
	GiftService giftService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Gift> getGiftById(@PathVariable("id") int id) {
		logger.info("Fetching Gift with id " + id);
        Gift gift = giftService.getGiftById(id);
        if (gift == null) {
            return new ResponseEntity<Gift>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Gift>(gift, HttpStatus.OK);
    }

    @GetMapping("gifts")
	public ResponseEntity<List<Gift>> getAllGifts() {
		List<Gift> list = giftService.getAllGifts();
		logger.info("Fetching all Gifts");
		return new ResponseEntity<List<Gift>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addgift", headers="Accept=application/json")
	public ResponseEntity<Void> addGift(@RequestBody Gift gift, UriComponentsBuilder ucBuilder) {
    	       giftService.addGift(gift);
    	       logger.info("Adding Gift to Database");
               HttpHeaders headers = new HttpHeaders();
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping("/updategift")
	public ResponseEntity<Gift> updateGift(@RequestBody Gift gift) {
    	Gift gift_details= giftService.updateGift(gift);
		logger.info("Updating Gift " + gift_details);
		return new ResponseEntity<Gift>(gift_details, HttpStatus.OK);
	}
    
    @DeleteMapping("/deletegift/{id}")
	public ResponseEntity<Void> deleteGift(@PathVariable("id") Integer id) {
    	giftService.deleteGift(id);
    	logger.info("Delete Gift Record from Database" + id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

}
