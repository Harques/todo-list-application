package com.microservices.user.controller;

import com.microservices.user.entity.User;
import com.microservices.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @CrossOrigin
    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> createUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email,@RequestParam("password") String password){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder().encode(password));
        User newUser = userService.createUser(user);
        if(newUser == null) return ResponseEntity.ok().body(false);
        return ResponseEntity.ok().body(true);
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login")
    public ResponseEntity<Integer> loginUser(@RequestParam("email") String email, @RequestParam("password") String password){
        User user = userService.findByEmail(email);
        if(user == null) return ResponseEntity.ok().body(0);
        else if(passwordEncoder().matches(password, user.getPassword())) return ResponseEntity.ok().body(user.getID().intValue());
        return ResponseEntity.ok().body(0);

    }
    @CrossOrigin(origins="*")
    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Long ID){
        return userService.findByID(ID);
    }
    @CrossOrigin(origins="*")
    @GetMapping("/")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/deleteAll")
    public void deleteAll(){
        userService.deleteAll();
    }
}
