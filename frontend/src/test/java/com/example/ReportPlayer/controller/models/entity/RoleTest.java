package com.example.ReportPlayer.controller.models.entity;

import com.example.ReportPlayer.models.role.Role;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RoleTest {

    private Role role;
    private Set<User> users;


    @BeforeEach
    public void init() {
        role = new Role();
        users = new HashSet<User>();


    }
    @Test
    public void checkAllAttribute() throws Exception {
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        users.add(user);
        role.setId(1L);
        role.setType("ROLE_USER");

        assertEquals(role.getType().toString(),"ROLE_USER");
        assertEquals(role.getId(),(Long)1L);




    }

}
