package com.microservices.auth.models;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponse implements Serializable {
    private String jwtToken;
    private Integer userId;
    private int status;
}
