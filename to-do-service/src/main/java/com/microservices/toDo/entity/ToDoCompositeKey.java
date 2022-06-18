package com.microservices.toDo.entity;

import java.io.Serializable;

public class ToDoCompositeKey implements Serializable {
    private Long id;
    private Long toDoListid;
}