package com.example.ReportPlayer.controller.repository.user;

import com.example.ReportPlayer.models.role.Role;
import com.example.ReportPlayer.repository.user.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {


    @Autowired
    private RoleRepository repository;

    @Autowired
    private TestEntityManager entityManager;




    @Test
    public void shouldFetchRoleByName() throws Exception {

        Role  actual = repository.getOne(1L);

        assertNotNull(actual);
        assertEquals(actual.getType().toString(),"ROLE_USER");
    }
}
