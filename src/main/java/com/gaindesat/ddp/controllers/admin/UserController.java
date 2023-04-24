package com.gaindesat.ddp.controllers.admin;


import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.repository.UserRepository;
import com.gaindesat.ddp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/users")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<User>> getAllUsers() {

        Iterable<User> allUsers = this.userRepository.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable Long userId) {
        Optional<User> optionalUser = this.userRepository.findById(userId);

        return optionalUser.<ResponseEntity<Object>>map(user ->
                new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("{User not found}", HttpStatus.NOT_FOUND));
    }
    @PostMapping("/users")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        User persistenceUser = userService.populateUser(userDTO, new User());
        userRepository.save(persistenceUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(persistenceUser.getId())
                .toUri();
        responseHeaders.setLocation(newUserUri);
        return new ResponseEntity<>(persistenceUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User persistenceUser = userService.populateUser(userDTO, optionalUser.get());
            userRepository.save(persistenceUser);

            return new ResponseEntity<>(persistenceUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found !", HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);

        return new ResponseEntity<>("user removed successfully", HttpStatus.OK);
    }
}
