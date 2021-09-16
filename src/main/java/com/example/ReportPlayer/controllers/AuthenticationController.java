package com.example.ReportPlayer.controllers;

import com.example.ReportPlayer.dto.request.AuthRequest;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = { "https://reporekt.com","https://www.reporekt.com","http://localhost:3000","http://localhost:8080"}, maxAge = 3600)
@RequestMapping("/api/v1/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;



    @PostMapping(value = "authentication/")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if(!userDetails.isAccountNonBanned()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if(userDetails==null) {
            return ResponseEntity.notFound().build();
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

}
