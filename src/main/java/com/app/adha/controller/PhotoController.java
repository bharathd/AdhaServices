package com.app.adha.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.adha.entity.Photo;
import com.app.adha.service.PhotoService;
import com.app.adha.service.PhotoStorageService;


@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

	@Autowired
	PhotoService photoService;
	
	@Autowired
    private PhotoStorageService photoStorageService;
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "./photos/";
    
    
    /*@RequestMapping("/up")
	public ModelAndView showUpload() {
		return new ModelAndView("upload");
	
    }
	*/
	/*@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Photo> getPhotoById(@PathVariable("id") int id) {
        System.out.println("Fetching Photo with id " + id);
        Photo photo = photoService.getPhotoById(id);
        if (photo == null) {
            return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Photo>(photo, HttpStatus.OK);
    }*/

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Photo>> getAllPhotosByUserId(@PathVariable("id") int UserId) {
		List<Photo> list = photoService.getAllPhotosByUserId(UserId);
		return new ResponseEntity<List<Photo>>(list, HttpStatus.OK);
	}
    
    /*@PostMapping(value="/addphoto", headers="Accept=application/json")
	public ResponseEntity<Void> addPhoto(@RequestBody Photo photo, UriComponentsBuilder ucBuilder) {
    	       photoService.addPhoto(photo);
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(photo.getPhotoId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }*/
    
    @DeleteMapping("/deletephoto/{id}")
	public ResponseEntity<Void> deletePhoto(@PathVariable("id") Integer id) {
    	photoService.deletePhoto(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
    
    
    
    @PostMapping("/uploadPhoto")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) {
        photoStorageService.storeFile(file);
        
        String[] parts = file.getOriginalFilename().split("_");
       
        Photo photo = new Photo();
        photo.setUserId(Integer.parseInt(parts[0]));
        photo.setUplodedBy(Integer.parseInt(parts[1]));
        photo.setProfilePhoto(Integer.parseInt(parts[2]));
        photo.setPhotoURL(UPLOADED_FOLDER+file.getOriginalFilename());
        
        photoService.addPhoto(photo);
        return "Successfully Uploaded";
    }

    @PostMapping("/uploadMultiplePhotos")
    public String uploadMultiplePhotos(@RequestParam("files") MultipartFile[] files) {
         Arrays.asList(files)
                .stream()
                .map(file -> uploadPhoto(file))
                .collect(Collectors.toList());
         
         return "Successfully Uploaded";
    }

    

}
