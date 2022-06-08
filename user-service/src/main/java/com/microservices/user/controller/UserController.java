package com.microservices.user.controller;

import com.microservices.user.entity.User;
import com.microservices.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

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
    public ResponseEntity<Integer> loginUser(@RequestParam("email") String email, @RequestParam("password") String password){
        User user = userService.findByEmail(email);
        if(user == null) return ResponseEntity.badRequest().build();
        else if(user.getPassword().equals(password)) return ResponseEntity.ok().body(user.getID().intValue());
        return ResponseEntity.badRequest().build();

    }
    @CrossOrigin(origins="*")
    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Long ID){
        return userService.findByID(ID);
    }
}
