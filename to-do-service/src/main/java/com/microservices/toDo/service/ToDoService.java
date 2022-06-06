package com.microservices.toDo.service;

import com.microservices.toDo.entity.ToDo;
import com.microservices.toDo.entity.ToDoList;
import com.microservices.toDo.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ToDoService {

    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoService(ToDoListRepository toDoListRepository){
        this.toDoListRepository = toDoListRepository;
    }

    public void createToDoList(Long userID){
        ToDoList toDoList = new ToDoList();
        toDoList.setUserID(userID);
        toDoListRepository.save(toDoList);
    }
    @Transactional
    public void addToDo(ToDo toDo, Long ID){
        ToDoList toDoList = toDoListRepository.findByID(ID);
        toDoList.getToDos().add(toDo);
        toDoListRepository.save(toDoList);
    }
    public ToDoList findListByID(Long ID){
        return toDoListRepository.findByID(ID);
    }
//    public ToDo findByID(Long ID){
//        return toDoListRepository.findByID(ID);
//    }

}