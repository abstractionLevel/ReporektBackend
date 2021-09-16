package com.example.ReportPlayer.config;

import com.example.ReportPlayer.exception.UserNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Questo metodo e' invocato quando un utente tenta di accedere ad un endpoint nn pubblico senza credenziali corrette
        System.out.println(authException.getMessage());
        if(authException.getMessage().contains("User account is locked")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "acc locked");
        }
        if(authException.getMessage().contains("Bad credentials")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "acc disabled");
        }
        if(authException.getMessage().contains("Full authentication is required to access this resource")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "acc disabled");
        }

    }
}