package com.example.ReportPlayer.controller.services.user;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.role.Role;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.services.user.UserDetailServiceImpl;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    @Qualifier("user_repository_"+ Server.Region.EUW)
    private UserBaseRepository userRepository;

    @Mock
    private UserDetails userDetails;

    @Mock
    private ApplicationContext context;



    @Test
    public void shouldReturnUserDetails() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        //act
        when(context.getBean(anyString())).thenReturn(userRepository);
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        //assert
        CustomUserDetails userDetails = (CustomUserDetails) userDetailService.loadUserByUsername("skynet"+Character.LINE_SEPARATOR+Server.Region.EUW);
        assertEquals(userDetails.getPassword(),"Tricolore99");
        verify(userRepository,times(1)).findByUsername(anyString());
    }

    @Test
    public void shouldThrowErrorWhenUserNotExist() throws Exception {
        //given
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        Set<Role> roles = new HashSet<>();

        //when
        when(userRepository.findByUsername("sky")).thenReturn(user);

        //then
        assertThrows(UsernameNotFoundException.class,() -> {
            userDetailService.loadUserByUsername("skyt");
        });
        verify(userRepository,times(1)).findByUsername(anyString());
    }


    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void userDetail_ShouldReturnUser(String region) throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        //act
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        final UserDetails userDetails = userDetailService.loadUserByUsername("apollo");
        //assert
        assertEquals(userDetails.getUsername(),"apollo");
        System.out.println(userRepository);
    }




    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }

}
