package com.example.ReportPlayer.filter;


import com.example.ReportPlayer.exception.UserException;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.services.user.UserServiceImpl;
import com.example.ReportPlayer.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.auth.login.AccountLockedException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = request.getHeader("Bearer");
        CustomUserDetails userDetails = null;
        if(authToken != null){
            userDetails = jwtTokenUtil.getUserDetails(authToken);

        }
        if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Ricostruisco l userdetails con i dati contenuti nel token
            // controllo integrita' token
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
