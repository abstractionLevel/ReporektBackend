package com.example.ReportPlayer.services.user;


import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.dto.password.ChangePasswordDto;
import com.example.ReportPlayer.dto.user.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDto user);
    void delete(User user);
    User findByEmail(String email);
    List<User> findAllUsers();
    User update(User user);
    Optional<User> findById(Long id);
    User findByUsername(String nickname);
    boolean updatePassword(ChangePasswordDto changePasswordDto);
    User activateUser(User user);
}
