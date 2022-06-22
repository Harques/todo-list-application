package com.microservices.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo{
    private Long id;
    private Long toDoListid;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private String text;
    private Boolean completed;

}