package com.microservices.toDo.service;

import com.microservices.toDo.dto.ToDoListResponse;
import com.microservices.toDo.entity.ToDo;
import com.microservices.toDo.entity.ToDoList;
import com.microservices.toDo.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoService(ToDoListRepository toDoListRepository){
        this.toDoListRepository = toDoListRepository;
    }

    public ToDoList createToDoList(String userEmail, String name){
        ToDoList toDoList = new ToDoList();
        toDoList.setUserEmail(userEmail);
        toDoList.setName(name);
        return toDoListRepository.save(toDoList);
    }
    @Transactional
    public void addToDo(ToDo toDo, Long ID){
        ToDoList toDoList = toDoListRepository.findByID(ID);
        toDoList.getToDos().add(toDo);
        toDoListRepository.save(toDoList);
    }
    public List<ToDoListResponse> findAllLists(String userEmail){
        List<ToDoList> toDoLists = toDoListRepository.findAllByUserEmail(userEmail);
        List<ToDoListResponse> response = toDoLists.stream().
                map(s -> {
                    ToDoListResponse toDoListResponse = new ToDoListResponse();
                    toDoListResponse.setName(s.getName());
                    toDoListResponse.setID(s.getID());
                    toDoListResponse.setToDos(s.getToDos());
                    return toDoListResponse;
                }).collect(Collectors.toList());
        return response;
    }
    public ToDoList findListByID(Long ID){
        return toDoListRepository.findByID(ID);
    }
//    public ToDo findByID(Long ID){
//        return toDoListRepository.findByID(ID);
//    }

}