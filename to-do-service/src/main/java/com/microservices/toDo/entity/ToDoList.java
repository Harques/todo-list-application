package com.microservices.toDo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "ToDoList")
public class ToDoList{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @ElementCollection
    @OneToMany(cascade=CascadeType.ALL)
    private List<ToDo> toDos;

    private Long userID;
    public ToDoList(){
        toDos = new ArrayList<>();
    }

}