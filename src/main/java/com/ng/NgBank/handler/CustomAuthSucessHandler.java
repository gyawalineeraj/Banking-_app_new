package com.ng.NgBank.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthSucessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String type = request.getHeader("X-Request-Type");
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                response.setContentType("application/json");
                String jsonResponse = "{\"message\" : \"login sucessful\"}";
                response.getWriter().write(jsonResponse);

    }
}
