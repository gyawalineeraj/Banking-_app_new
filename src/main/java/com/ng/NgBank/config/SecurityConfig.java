package com.ng.NgBank.config;

import com.ng.NgBank.handler.CustomAuthSucessHandler;
import com.ng.NgBank.filter.CustomLoggingFilter;
import com.ng.NgBank.handler.CustomAuthFailerHander;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@Data
public class SecurityConfig {


    private final AuthenticationManager authenticationManager;
    private final CustomLoggingFilter customLoggingFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {


        http.csrf((c) -> c.disable())
                .authenticationManager(authenticationManager)
                .httpBasic(c-> c.disable())
                .formLogin(f -> {
                    f.loginProcessingUrl("/testlogin")
                            .successHandler(new CustomAuthSucessHandler())
                            .failureHandler(new CustomAuthFailerHander())
                            .permitAll();
                })
                .sessionManagement(s -> s.sessionCreationPolicy(
                        SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(
                        (c) -> c
                                .requestMatchers("/account/**")
                                .authenticated()
                                .requestMatchers("/admin/**")
                                .hasAuthority("ADMIN")
                                .anyRequest().permitAll());

        http.addFilterBefore(customLoggingFilter,
                SecurityContextPersistenceFilter.class);

        return http.build();

    }


}
