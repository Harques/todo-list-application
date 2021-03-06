package com.microservices.user.service;

import com.microservices.user.dto.CreateUser;
import com.microservices.user.entity.User;
import com.microservices.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
    public User findByID(Long ID){
        return userRepository.findByID(ID);
    }
    public User findByEmail(String email){return userRepository.findByEmail(email);}
    public List<User> findAll(){return userRepository.findAll();}

    public void deleteAll(){ userRepository.deleteAll();}

}
