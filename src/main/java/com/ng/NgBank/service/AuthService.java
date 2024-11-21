package com.ng.NgBank.service;

import com.ng.NgBank.dto.RegisterDto;
import com.ng.NgBank.entity.Account;
import com.ng.NgBank.entity.Role;
import com.ng.NgBank.entity.User;
import com.ng.NgBank.repository.RoleRepository;
import com.ng.NgBank.repository.UserRepository;
import com.ng.NgBank.util.UserMapper;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class AuthService {

    private final UserRepository userRepository;
    private final AccountCreationService accountCreationService;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public void registerUser(RegisterDto registerDto) {
        System.out.println("register dto " + registerDto);
        User user = userMapper.registerDtoToUser(registerDto);
        System.out.println("user  " + user);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Account account = accountCreationService.getSavingAccount(0.00,0.00);
        Role role =
                roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException(
                        "Role Does not Exist"));
        user.setAccount(account);
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }


    public User getUser(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new InvalidParameterException(
                "Invalid user email"));


    }

}
