package com.ng.NgBank.controller;

import com.ng.NgBank.dto.LoginDto;
import com.ng.NgBank.dto.RegisterDto;
import com.ng.NgBank.response.CommonResponse;
import com.ng.NgBank.response.LoginResponse;
import com.ng.NgBank.service.AuthService;
import com.ng.NgBank.util.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping("/auth")
@Tag(name = "'AuthorizationSevices")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterDto registerDto) {

        authService.registerUser(registerDto);
        return ResponseEntity.ok("Signup Successfull");
    }



}
