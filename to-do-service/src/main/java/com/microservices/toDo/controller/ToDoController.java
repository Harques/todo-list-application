package com.microservices.toDo.controller;

import com.microservices.toDo.dto.ToDoListRequest;
import com.microservices.toDo.dto.ToDoListResponse;
import com.microservices.toDo.entity.ToDo;
import com.microservices.toDo.entity.ToDoList;
import com.microservices.toDo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }
    @CrossOrigin("*")
    @PostMapping("/{id}")
    public void addTodo(@RequestBody ToDo todo, @PathVariable("id") Long ID){
        toDoService.addToDo(todo, ID);
    }
    @CrossOrigin("*")
    @PostMapping("user")
    public ResponseEntity<Boolean> createList(@RequestBody ToDoListRequest dto){
        ToDoList toDoList = toDoService.createToDoList(dto.getUserEmail(), dto.getName());
        if(toDoList == null) return ResponseEntity.ok().body(false);
        return ResponseEntity.ok().body(true);
    }
    @CrossOrigin("*")
    @GetMapping("list/{email}")
    public List<ToDoListResponse> getAllLists(@PathVariable("email") String userEmail){
        return toDoService.findAllLists(userEmail);
    }

}
