package com.app.adha.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.nio.file.Path;
import org.springframework.util.StreamUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
import org.springframework.core.io.ClassPathResource;

import com.app.adha.entity.Photo;
import com.app.adha.exception.FileStorageException;
import com.app.adha.service.PhotoService;



@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

	@Autowired
	PhotoService photoService;
	
	
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Photo>> getAllPhotosByUserId(@PathVariable("id") int UserId) {
		List<Photo> list = photoService.getAllPhotosByUserId(UserId);
		return new ResponseEntity<List<Photo>>(list, HttpStatus.OK);
	}
    
   /* @GetMapping(value = "/{userid}/{photoname}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("userid") int UserId, @PathVariable("photoname") String photoName) throws IOException { 
    	
        String photo_location = UserId +"/"+ photoName;
        ClassPathResource imgFile = new ClassPathResource("photos/"+photo_location);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }*/
    
    @GetMapping(value = "/photos/{userid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("userid") int UserId) throws IOException { 
    	List<Photo> list = photoService.getAllPhotosByUserId(UserId);
    	byte[] bytes = new byte[50];
    	for(Photo photo : list) {
    		String[] photo_name = photo.getPhotoURL().split("/");
        String photo_location = UserId +"/"+ photo_name[1];
        ClassPathResource imgFile = new ClassPathResource("photos/"+photo_location);
        bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
    	}
        return new ResponseEntity<byte[]>(bytes,HttpStatus.OK);
               // .ok()
               // .contentType(MediaType.IMAGE_JPEG)
               // .body(bytes);
    }
    
    @DeleteMapping("/deletephoto/{id}")
	public ResponseEntity<Void> deletePhoto(@PathVariable("id") Integer id) {
    	photoService.deletePhoto(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
    
    
    
    @PostMapping("/uploadPhoto")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, String newname) {
    	
        photoService.storeFile(file, newname);
        logger.info("Added photo");
        return "Successfully Uploaded";
    }

    @PostMapping("/uploadPhotos")
    public String uploadMultiplePhotos(@RequestParam("files") MultipartFile[] files, @RequestParam("newname") String newname) {
         Arrays.asList(files)
                .stream()
                .map(file -> uploadPhoto(file, newname))
                .collect(Collectors.toList());
        
         return "Successfully Uploaded";
    }

    

}
