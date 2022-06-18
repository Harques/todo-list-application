package com.microservices.toDo.service;

import com.microservices.toDo.dto.ToDoListRequest;
import com.microservices.toDo.dto.ToDoListResponse;
import com.microservices.toDo.dto.ToDoRequest;
import com.microservices.toDo.entity.ToDo;
import com.microservices.toDo.entity.ToDoList;
import com.microservices.toDo.repository.ToDoListRepository;
import org.hibernate.Hibernate;
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

    public ToDoList createToDoList(String userEmail, String name, Long ID){
        ToDoList toDoList = new ToDoList();
        toDoList.setUserEmail(userEmail);
        toDoList.setName(name);
        toDoList.setID(ID);
        return toDoListRepository.save(toDoList);
    }
    @Transactional
    public void addToDo(ToDoRequest toDoRequest){
        ToDoList toDoList = toDoListRepository.findListByUserEmailAndID(toDoRequest.getUserEmail(), toDoRequest.getToDoListid());
        ToDo toDo = new ToDo();
        toDo.setId(toDoRequest.getId());
        toDo.setDate(toDoRequest.getDate());
        toDo.setCompleted(toDoRequest.getCompleted());
        toDo.setText(toDoRequest.getText());
        toDo.setToDoListid(toDoRequest.getToDoListid());
        toDoList.getToDos().add(toDo);
        toDoListRepository.save(toDoList);
    }

    @Transactional
    public void clearToDo(ToDoRequest toDoRequest){
        ToDoList toDoList = toDoListRepository.findListByUserEmailAndID(toDoRequest.getUserEmail(), toDoRequest.getToDoListid());
        toDoList.setToDos(toDoList.getToDos().stream().filter(t -> t.getId() != toDoRequest.getId()).collect(Collectors.toList()));
        toDoListRepository.save(toDoList);

    }
    @Transactional
    public void completeToDo(ToDoRequest toDoRequest){
        ToDoList toDoList = toDoListRepository.findListByUserEmailAndID(toDoRequest.getUserEmail(), toDoRequest.getToDoListid());
        toDoList.setToDos(toDoList.getToDos().stream().map( t -> {
            if(t.getId() == toDoRequest.getId()){
                t.setCompleted(!t.getCompleted());
            }
            return t;
        }).collect(Collectors.toList()));
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
    public void deleteAllLists(String userEmail){
        toDoListRepository.deleteAll();
    }

    public void deleteList(ToDoListRequest dto){
        ToDoList toDoList = toDoListRepository.findListByUserEmailAndID(dto.getUserEmail(), dto.getId());
        toDoListRepository.delete(toDoList);
    }

    public ToDoList findListByID(Long ID){
        return toDoListRepository.findByID(ID);
    }
//    public ToDo findByID(Long ID){
//        return toDoListRepository.findByID(ID);
//    }

}