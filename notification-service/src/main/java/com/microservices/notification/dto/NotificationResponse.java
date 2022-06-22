package com.microservices.notification.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class NotificationResponse{
    private String name;
    private List<String> toDos;
    public NotificationResponse(){
        toDos = new ArrayList<>();
    }
}