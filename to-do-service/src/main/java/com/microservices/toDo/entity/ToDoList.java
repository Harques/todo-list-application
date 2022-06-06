package com.microservices.toDo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<ToDo> toDos;
    public ToDoList(){
        toDos = new ArrayList<ToDo>();
    }

}