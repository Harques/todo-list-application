package com.microservices.toDo.repository;

import com.microservices.toDo.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    ToDoList findByID(Long ID);

    @Query("SELECT t FROM ToDoList t WHERE t.userEmail = ?1")
    List<ToDoList> findAllByUserEmail(String userEmail);
}