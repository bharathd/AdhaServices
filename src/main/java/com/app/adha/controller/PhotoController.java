package com.app.adha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.Photo;
import com.app.adha.service.PhotoService;

@RestController
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	PhotoService photoService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Photo> getPhotoById(@PathVariable("id") int id) {
        System.out.println("Fetching Photo with id " + id);
        Photo photo = photoService.getPhotoById(id);
        if (photo == null) {
            return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Photo>(photo, HttpStatus.OK);
    }

    @GetMapping("photos")
	public ResponseEntity<List<Photo>> getAllPhotos() {
		List<Photo> list = photoService.getAllPhotos();
		return new ResponseEntity<List<Photo>>(list, HttpStatus.OK);
	}
    
    @PostMapping(value="/addphoto", headers="Accept=application/json")
	public ResponseEntity<Void> addPhoto(@RequestBody Photo photo, UriComponentsBuilder ucBuilder) {
    	       photoService.addPhoto(photo);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(photo.getPhotoId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/deletephoto/{id}")
	public ResponseEntity<Void> deletePhoto(@PathVariable("id") Integer id) {
    	photoService.deletePhoto(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

}
