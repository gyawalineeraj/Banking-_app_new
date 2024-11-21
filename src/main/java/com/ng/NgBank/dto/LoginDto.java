package com.ng.NgBank.dto;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    private String username;
    private String password;
}
