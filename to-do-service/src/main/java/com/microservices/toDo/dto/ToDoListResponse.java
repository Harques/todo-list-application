package com.microservices.toDo.dto;

import com.microservices.toDo.entity.ToDo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToDoListResponse{
    private Long ID;
    private String name;
    private List<ToDo> toDos;

}