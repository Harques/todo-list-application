package com.microservices.toDo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ToDoCompositeKey.class)
public class ToDo{
    @Id
    private Long id;
    @Id
    private Long toDoListid;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private String text;
    private Boolean completed;

}