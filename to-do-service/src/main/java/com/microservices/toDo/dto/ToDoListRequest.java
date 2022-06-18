package com.microservices.toDo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ToDoListRequest {
    private String name;
    private String userEmail;
    private Long id;

}