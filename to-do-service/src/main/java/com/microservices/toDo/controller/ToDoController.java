package com.microservices.toDo.controller;

import com.microservices.toDo.entity.ToDoList;
import com.microservices.toDo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }
    @PostMapping
    public String addTodo(){
        return "";
    }
    @GetMapping
    public void createList(){
        toDoService.createToDoList();
    }
    @GetMapping("/{id}")
    public ToDoList getList(@PathVariable("id") Long ID){
        return toDoService.findListByID(ID);
    }
}
