package com.example.ReportPlayer.controller.repository.user;

import com.example.ReportPlayer.models.role.Role;
import com.example.ReportPlayer.repository.user.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryInTest {

    @Autowired
    private RoleRepository repository;



    @Test
    public void shouldFetchRoleByName() throws Exception {
        //arrange
        //act
        Role role = repository.findByType("ROLE_USER");
        //assert
        assertEquals(role.getType(),"ROLE_USER");

    }
}
