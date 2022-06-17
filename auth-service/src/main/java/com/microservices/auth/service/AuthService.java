package com.microservices.auth.service;

import com.microservices.auth.entity.AuthLogin;
import com.microservices.auth.entity.AuthRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public ResponseEntity<Boolean> register(AuthRegister authRegister){
        MultiValueMap<String, String> registerParameters = new LinkedMultiValueMap<String, String>();
        registerParameters.add("firstName", authRegister.getFirstName());
        registerParameters.add("lastName", authRegister.getLastName());
        registerParameters.add("email", authRegister.getEmail());
        registerParameters.add("password", authRegister.getPassword());

        return restTemplate.postForEntity("http://localhost:9501/register", registerParameters, Boolean.class);
    }

    public ResponseEntity<Integer> login(AuthLogin authLogin){
        MultiValueMap<String, String> loginParameters = new LinkedMultiValueMap<String, String>();
        loginParameters.add("email", authLogin.getEmail());
        loginParameters.add("password", authLogin.getPassword());

        return restTemplate.postForEntity("http://localhost:9501/login", loginParameters, Integer.class);



    }

}
