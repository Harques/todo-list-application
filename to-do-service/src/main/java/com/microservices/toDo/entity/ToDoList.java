package com.microservices.toDo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.util.annotation.Nullable;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "ToDoList")
public class ToDoList{

    @Id
    private Long ID;
    @ElementCollection
    @OneToMany(cascade=CascadeType.ALL)
    private List<ToDo> toDos;

    private String name;

    private String userEmail;
    public ToDoList(){
        toDos = new ArrayList<>();
    }

}