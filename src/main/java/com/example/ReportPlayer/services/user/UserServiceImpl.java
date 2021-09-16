package com.example.ReportPlayer.services.user;

import com.example.ReportPlayer.exception.InvalidOldPasswordException;
import com.example.ReportPlayer.exception.UserAlreadyExistException;
import com.example.ReportPlayer.exception.UserException;
import com.example.ReportPlayer.exception.UserNotFoundException;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.dto.password.ChangePasswordDto;
import com.example.ReportPlayer.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBaseRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User save(UserDto userDto) {
        final User user = UserBuilder.newBuilder().username(userDto.getUsername()).
                email(userDto.getEmail()).password(userDto.getPassword()).confirmPassword(userDto.getConfirmPassword()).isActive(false).build();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(emailExists(user.getEmail())) {
            throw new UserAlreadyExistException("User with email " + user.getEmail() + "already exists");
        }
        if(usernameExists(user.getUsername())) {
            throw new UserAlreadyExistException("user whit username " + user.getUsername() + "already in use");
        }
        return  userRepository.save(user);
    }



    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByEmail(String email) {
        final User user =  userRepository.findByEmail(email);
        if(user == null) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return (User) userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User findByUsername(String username) {
        final User user =  userRepository.findByUsername(username);
        if(user==null) {
            return null;
        }
        return user;
    }


    @Override
    public boolean updatePassword(ChangePasswordDto changePasswordDto) {
        final User user = userRepository.findByUsername(changePasswordDto.getUsername());
        boolean password = bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(),user.getPassword());
        if(!password) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(changePasswordDto.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User activateUser(User user) {
        user.setEnabled(true);
        return (User) userRepository.save(user);
    }



    private boolean emailExists(String email) {
        final User user = (User) userRepository.findByEmail(email);
        if(user != null) {
            return true;
        }
        return false;
    }

    private boolean usernameExists(String username) {
        final User user = (User) userRepository.findByUsername(username);
        if(user != null) {
            return true;
        }
        return false;
    }


}
