package com.example.ReportPlayer.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isAccountNonBanned;

    public CustomUserDetails(String username) {
        this.username = username;
    }


    public CustomUserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CustomUserDetails(String username, String password, boolean isAccountNonBanned) {
        this.username = username;
        this.password = password;
        this.isAccountNonBanned = isAccountNonBanned;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public boolean isAccountNonBanned() {
        return isAccountNonBanned;
    }

    public void setAccountNonBanned(boolean accountNonBanned) {
        isAccountNonBanned = accountNonBanned;
    }
}
