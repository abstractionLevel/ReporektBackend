package com.example.ReportPlayer.controller.repository.user;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.user.UserRepositoryFactory;
import com.example.ReportPlayer.models.reportTypeRegion.ReportTypeRegion;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.reportTypeOfRegion.ReportTypeRegionBaseRepository;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;

import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserBaseRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReportTypeRegionBaseRepository repository;


    @Test
    public void save_ShouldSave() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();

        //act
        userRepository.save(user);
        final User expected =  userRepository.findByUsername(user.getUsername());
        //asset
        assertEquals(expected.getUsername(),user.getUsername());
        assertNotNull(expected);
    }

    @Test
    public void save_ShouldSave1() {
        //arrange
        ReportTypeRegion reportTypeRegion = new ReportTypeRegion("Afk",10,"euw");
        //act
        final ReportTypeRegion reportTypeRegionExpexted = repository.save(reportTypeRegion);
        //assert
        System.out.println(reportTypeRegionExpexted);
        Assert.assertNotNull(reportTypeRegionExpexted);
    }

    @Test
    public void delete_ShouldDelete() throws Exception {
        //assert
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        entityManager.persist(user);
        //act
        final User userSaved=  userRepository.findByUsername("apollo");
        userRepository.delete(userSaved);
        final User userExpected =  userRepository.findByUsername("apollo");

        //arrange
        assertNull(userExpected);

    }

    @Test
    public void findUser_ShouldFindUserById() throws Exception {
        //assert
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        //act
        final Optional<User> expected = userRepository.findById(user.getId());

        //arrange
        assertNotNull(expected.isPresent());
    }


    @Test
    public void findUser_ShouldFindUserByUsername() throws Exception {
        //assert
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        //act
        final User userSaved = entityManager.persistAndFlush(user);
        final User expected =  userRepository.findByUsername(user.getUsername());
        //arrange
        assertEquals(user.getUsername(),expected.getUsername());
    }

    @Test
    public void findUser_ShouldReturnNullIfUserNotExistWhenFindByUsername() throws Exception {
        //assert
        //act
        User user =  userRepository.findByUsername("skyy");
        //arrange
        assertEquals(null,user);
    }

    @Test
    public void findUser_ShouldReturnNullIfUserNotExistWhenFindByEmail() throws Exception {
        //assert
        final User expected =  userRepository.findByEmail("deluciaugo@gmai.com");
        //act
        //arrange
        assertNull(null,expected);
    }



    @Test
    public void updateUser_ShouldUpdateUser() throws Exception {
        //assert
        final User userBuilder = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        final User user = entityManager.persistAndFlush(userBuilder);
        final User expected =  userRepository.findByUsername(user.getUsername());
        expected.setUsername("jhion");
        expected.setPassword("napolisovvert11");
        expected.setEmail("deluciaugo@gmail.com");
        //act
        //arrange
        assertEquals(expected.getUsername(),"jhion");
        assertEquals(expected.getPassword(),"napolisovvert11");
        assertEquals(expected.getEmail(),"deluciaugo@gmail.com");

    }


}
