package com.example.ReportPlayer.services.user;

import com.example.ReportPlayer.exception.UserNotFoundException;
import com.example.ReportPlayer.factory.user.UserRepositoryFactory;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
import com.example.ReportPlayer.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.security.auth.login.AccountLockedException;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptyList;


@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserBaseRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
        final User user =  userRepository.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("");
        }
        return new CustomUserDetails(user.getUsername(),user.getPassword(),user.isAccountNonLocked());
    }




}
