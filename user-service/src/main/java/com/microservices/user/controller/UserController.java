package com.microservices.user.controller;

import com.microservices.user.dto.CreateUser;
import com.microservices.user.entity.User;
import com.microservices.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @CrossOrigin(origins="*")
    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User createUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email,@RequestParam("password") String password){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return userService.createUser(user);
    }
    @CrossOrigin(origins="*")
    @PostMapping(value = "/login",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password){
        User user = userService.findByEmail(email);
        if(user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("HTTP Status will be UNAUTHORIZED (CODE 401)\n");
        else if(user.getPassword().equals(password)) return ResponseEntity.status(HttpStatus.OK).body("HTTP Status will be CREATED (CODE 201)\n");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("HTTP Status will be UNAUTHORIZED (CODE 401)\n");

    }
    @CrossOrigin(origins="*")
    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Long ID){
        return userService.findByID(ID);
    }
}
