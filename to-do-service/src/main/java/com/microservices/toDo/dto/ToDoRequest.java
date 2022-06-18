package com.microservices.toDo.dto;

import lombok.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoRequest {
    private Long id;
    private Date date;
    private String text;
    private Boolean completed;
    private String userEmail;
    private Long toDoListid;

}
