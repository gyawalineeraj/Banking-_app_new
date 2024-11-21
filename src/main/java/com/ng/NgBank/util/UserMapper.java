package com.ng.NgBank.util;

import com.ng.NgBank.dto.RegisterDto;
import com.ng.NgBank.entity.Account;
import com.ng.NgBank.entity.User;
import com.ng.NgBank.response.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public LoginResponse userAndAccountToLoginResponse(User user,
                                                       Account account) {
        return LoginResponse.builder()

                .build();
    }

    public User registerDtoToUser(RegisterDto registerDto) {
        return User.builder()
                .email(registerDto.getEmail())
                .dob(registerDto.getDob())
                .address(registerDto.getAddress())
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .gender(registerDto.getGender())
                .dob(registerDto.getDob())
                .password(registerDto.getPassword())
                .build();
    }

}
