package com.microservices.notification.dto;

import lombok.*;

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