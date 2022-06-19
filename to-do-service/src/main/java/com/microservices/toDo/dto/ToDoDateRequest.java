package com.microservices.toDo.dto;

import lombok.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDateRequest {
    private Long id;
    private String date;
    private String userEmail;
    private Long toDoListid;

}
