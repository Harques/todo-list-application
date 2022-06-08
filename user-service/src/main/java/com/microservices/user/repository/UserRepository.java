package com.microservices.user.repository;

import com.microservices.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByID(Long ID);
    @Query("SELECT t FROM User t WHERE t.email = ?1")
    User findByEmail(String email);
}