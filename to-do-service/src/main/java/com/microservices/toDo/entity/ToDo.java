package com.microservices.toDo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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
    private Date date;
    private String text;
    private Boolean completed;

}