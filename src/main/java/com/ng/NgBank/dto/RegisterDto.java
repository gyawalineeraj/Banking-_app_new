package com.ng.NgBank.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String dob;
    private String gender;
}
