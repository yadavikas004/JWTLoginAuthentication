package com.jwt.authentication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String about;
    private String role; //if needed while registration


}
