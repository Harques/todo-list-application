package com.microservices.toDo.controller;

import com.microservices.toDo.dto.ToDoDateRequest;
import com.microservices.toDo.dto.ToDoListRequest;
import com.microservices.toDo.dto.ToDoListResponse;
import com.microservices.toDo.dto.ToDoRequest;
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
    @PostMapping("add")
    public void addTodo(@RequestBody ToDoRequest toDoRequest){
        toDoService.addToDo(toDoRequest);
    }
    @CrossOrigin("*")
    @PostMapping("delete")
    public void deleteTodo(@RequestBody ToDoRequest toDoRequest){
        toDoService.clearToDo(toDoRequest);
    }
    @CrossOrigin("*")
    @PostMapping("complete")
    public void completeTodo(@RequestBody ToDoRequest toDoRequest){
        toDoService.completeToDo(toDoRequest);
    }

    @CrossOrigin("*")
    @PostMapping("date")
    public void date(@RequestBody ToDoRequest toDoRequest) {
        toDoService.date(toDoRequest);}

    @CrossOrigin("*")
    @PostMapping("user")
    public ResponseEntity<Boolean> createList(@RequestBody ToDoListRequest dto){
        ToDoList toDoList = toDoService.createToDoList(dto.getUserEmail(), dto.getName(), dto.getId());
        if(toDoList == null) return ResponseEntity.ok().body(false);
        return ResponseEntity.ok().body(true);
    }
    @CrossOrigin("*")
    @GetMapping("list/{email}")
    public List<ToDoListResponse> getAllLists(@PathVariable("email") String userEmail){
        return toDoService.findAllLists(userEmail);
    }
    @CrossOrigin("*")
    @GetMapping("deleteAll/{email}")
    public void deleteAllLists(@PathVariable("email") String userEmail){
        toDoService.deleteAllLists(userEmail);
    }
    @CrossOrigin("*")
    @PostMapping("deleteList")
    public void deleteList(@RequestBody ToDoListRequest dto){
        toDoService.deleteList(dto);
    }

}
