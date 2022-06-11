package com.microservices.auth.controller;

import com.microservices.auth.entity.AuthLogin;
import com.microservices.auth.entity.AuthRegister;
import com.microservices.auth.models.AuthenticationStatus;
import com.microservices.auth.models.ErrorResponseDto;
import com.microservices.auth.models.JwtResponse;
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
    @CrossOrigin
    @PostMapping("/register")
    public void register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam("password") String password){
        AuthRegister authRegister = new AuthRegister(firstName, lastName, email, password);
        authService.register(authRegister);
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password){
        AuthLogin authLogin = new AuthLogin(email, password);
        ResponseEntity<Integer> response = authService.login(authLogin);
        if(response.getBody().intValue() == 0){
            ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED","aa");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
        final String token = jwtTokenUtil.generateToken(email);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
