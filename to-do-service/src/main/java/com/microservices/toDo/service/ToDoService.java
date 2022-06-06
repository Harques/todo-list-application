package com.microservices.toDo.service;

import com.microservices.toDo.entity.ToDo;
import com.microservices.toDo.entity.ToDoList;
import com.microservices.toDo.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoService(ToDoListRepository toDoListRepository){
        this.toDoListRepository = toDoListRepository;
    }

    public void createToDoList(){
        ToDoList toDoList = new ToDoList();
        toDoListRepository.save(toDoList);
    }
    public void addToDo(ToDo toDo, ToDoList toDoList){
        toDoList.getToDos().add(toDo);
    }
    public ToDoList findListByID(Long ID){
        return toDoListRepository.getReferenceById(ID);
    }
//    public ToDo findByID(Long ID){
//        return toDoListRepository.findByID(ID);
//    }

}