package com.app.adha.controller;

import com.app.adha.exception.ResourceNotFoundException;
import com.app.adha.model.User;
import com.app.adha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Anil on 03/10/18.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/notes")
    public List<User> getAllNotes() {
        return userRepository.findAll();
    }

    @PostMapping("/notes")
    public User createNote(@Valid @RequestBody User note) {
        return userRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public User getNoteById(@PathVariable(value = "id") Long noteId) {
        return userRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public User updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody User noteDetails) {

    	User user = userRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

    	user.setTitle(noteDetails.getTitle());
    	user.setContent(noteDetails.getContent());

        User updatedNote = userRepository.save(user);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
    	User user = userRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

    	userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}
