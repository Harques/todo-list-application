package com.microservices.user.controller;

import com.microservices.user.dto.CreateUser;
import com.microservices.user.entity.User;
import com.microservices.user.service.UserService;
import com.microservices.user.util.JwtUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class UserController {

    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

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
    public User loginUser(@RequestParam("email") String email, @RequestParam("password") String password){
        user
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Long ID){
        return userService.findByID(ID);
    }
}
