package com.ng.NgBank.userDetails;

import com.ng.NgBank.repository.UserRepository;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Data
public class DaoUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        var user =
                userRepository.findByEmail(email).orElseThrow(
                        () -> new UsernameNotFoundException(
                                "User with the email doesn not exist in the database"));
        return new CustomUserDetails(user);
    }
}
