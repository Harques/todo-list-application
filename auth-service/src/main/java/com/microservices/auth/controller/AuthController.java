package com.microservices.auth.controller;

import com.microservices.auth.entity.AuthLogin;
import com.microservices.auth.entity.AuthRegister;
import com.microservices.auth.models.*;
import com.microservices.auth.security.JwtTokenUtil;
import com.microservices.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.core.Response;
import java.util.Date;

@RestController
@RequestMapping("/")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @CrossOrigin("*")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam("password") String password){
        AuthRegister authRegister = new AuthRegister(firstName, lastName, email, password);
        ResponseEntity<Boolean> response =  authService.register(authRegister);
        if(response.getBody() == false){
            ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST","auth/login");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        RegisterResponse registerResponse = new RegisterResponse(HttpStatus.OK.value());
        return new ResponseEntity<>(registerResponse,HttpStatus.OK);
    }
    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password){
        AuthLogin authLogin = new AuthLogin(email, password);
        ResponseEntity<Integer> response = authService.login(authLogin);
        if(response.getBody().intValue() == 0){
            ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED","auth/login");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
        final String token = jwtTokenUtil.generateToken(email);
        LoginResponse loginResponse = new LoginResponse(token, response.getBody().intValue(), HttpStatus.OK.value());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}
