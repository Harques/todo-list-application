package com.microservices.toDo.controller;

import com.microservices.toDo.entity.ToDo;
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
    @PostMapping("/{id}")
    public void addTodo(@RequestBody ToDo todo, @PathVariable("id") Long ID){
        toDoService.addToDo(todo, ID);
    }
    @GetMapping("user/{id}")
    public void createList(@PathVariable("id") Long userID){
        toDoService.createToDoList(userID);
    }
    @GetMapping("list/{id}")
    public ToDoList getList(@PathVariable("id") Long ID){
        return toDoService.findListByID(ID);
    }

}
