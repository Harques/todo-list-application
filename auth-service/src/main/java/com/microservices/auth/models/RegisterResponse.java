package com.microservices.auth.models;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterResponse implements Serializable {
    private int status;
}
