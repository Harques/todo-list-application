package com.microservices.notification.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}