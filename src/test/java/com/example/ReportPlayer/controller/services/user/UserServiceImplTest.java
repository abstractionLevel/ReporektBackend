package com.example.ReportPlayer.controller.services.user;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.exception.UserAlreadyExistException;
import com.example.ReportPlayer.exception.UserException;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.user.RoleRepository;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
import com.example.ReportPlayer.services.email.EmailSenderService;
import com.example.ReportPlayer.services.email.TextEmailSenderServiceImpl;
import com.example.ReportPlayer.services.token.VerificationTokenService;
import com.example.ReportPlayer.services.user.UserServiceImpl;
import com.example.ReportPlayer.builder.password.ChangePasswordDtoBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.builder.user.UserDtoBuilder;
import com.example.ReportPlayer.dto.password.ChangePasswordDto;
import com.example.ReportPlayer.dto.user.UserDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {


    @InjectMocks
    private  UserServiceImpl userServiceImpl;

    @Mock
    private UserBaseRepository userRepository;

    @Mock
    private VerificationTokenService verificationTokenService;

    @Mock
    private EmailSenderService emailSenderService;

    @Mock
    private TextEmailSenderServiceImpl textEmailSenderServiceImpl;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private RoleRepository roleRepository;



    @Test
    public void save_ShouldRegisterUser() throws Exception {
        //arrange
        final UserDto userDto = UserDtoBuilder.newBuilder().email("Apollo@gmail.com").password("Apollo12").
                confirmPassword("Apollo12").username("Apollo").isActive(false).build();
        final User user = UserBuilder.newBuilder().username(userDto.getUsername()).
                email(userDto.getEmail()).password(userDto.getPassword()).confirmPassword(userDto.getConfirmPassword()).isActive(false).build();
        //act
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(user.getPassword());
        when(userRepository.save(any(User.class))).thenReturn(user);
        final User userSaved = userServiceImpl.save(userDto);
        //assert
        assertEquals(userSaved.getUsername(),"Apollo");
        assertEquals(userSaved.getEmail(),"Apollo@gmail.com");
        assertEquals(userSaved.getPassword(),"Apollo12");
        assertEquals(userSaved.getConfirmPassword(),"Apollo12");
    }



    @Test
    public void save_ShouldThrowUserAlreadyExistExceptionIfUsernameIsInUse() throws Exception {
        //arrange
        final UserDto userDto = UserDtoBuilder.newBuilder().username("Apollo11").
                password("Tricolore11").confirmPassword("Tricolore11").email("apollo@gmail.com").build();
        final User user = UserBuilder.newBuilder().username(userDto.getUsername()).
                email(userDto.getEmail()).password(userDto.getPassword()).confirmPassword(userDto.getConfirmPassword()).isActive(false).build();
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        //when(UserRepositoryFactory.getRepository(userDto.getRegion(),applicationContext)).thenReturn(userRepository);
        //act
        assertThrows(UserAlreadyExistException.class,() -> {
            userServiceImpl.save(userDto);
        });
        //assert
        verify(userRepository,times(1)).findByUsername(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void saver_ShouldThrowUserAlreadyExistExceptionIfEmailIsInUse() throws Exception {
        //arrange
        final UserDto userDto = UserDtoBuilder.newBuilder().username("Apollo11").
                password("Tricolore11").confirmPassword("Tricolore11").email("apollo@gmail.com").build();
        final User user = UserBuilder.newBuilder().username(userDto.getUsername()).
                email(userDto.getEmail()).password(userDto.getPassword()).confirmPassword(userDto.getConfirmPassword()).isActive(false).build();
        //when(UserRepositoryFactory.getRepository(userDto.getRegion(),applicationContext)).thenReturn(userRepository);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        //act
        assertThrows(UserAlreadyExistException.class,() -> {
            final User expected = userServiceImpl.save(userDto);
            //assert
            assertNotNull(expected);
        });
        //assert
        verify(userRepository, never()).save(any(User.class));
        verify(userRepository,times(1)).findByEmail(anyString());


    }

    @Test
    public void find_ShouldFindUserByUsername() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        //act
        final User expected = userServiceImpl.findByUsername("skynet");
        //assert
        assertNotNull(expected);
    }

    @Test
    public void find_ShouldThrowUserExceptionIfUserNotExist() throws Exception {
        //arrange
        //act
        //when(UserRepositoryFactory.getRepository(region,applicationContext)).thenReturn(userRepository);
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        //assert
        assertThrows(UserException.class,() -> {
            userServiceImpl.findByUsername("Trovaa92");
        });

        verify(userRepository,times(1)).findByUsername(anyString());


    }


    @Test
    public void find_ShouldFetchByEmail() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        //act
        final User expected = userServiceImpl.findByEmail("ugo@gmail");
        //assert
        assertNotNull(expected);
        verify(userRepository,times(1)).findByEmail(anyString());
    }


    @Test
    public void find_ShouldFetchById() throws Exception {
        //arrange
        final User userBuilder = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        final Optional<User> user = Optional.of(userBuilder);
        final Long id=1L;
        when(userRepository.findById(id)).thenReturn(user);
        //act
        Optional<User> expected = userServiceImpl.findById(id);
        //assert
        assertNotNull(expected);

    }

    @Test
    public void delete_ShouldDelete() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        //act
        doNothing().when(userRepository).delete(any(User.class));
        userServiceImpl.delete(user);
        //assert
        verify(userRepository,times(1)).delete(user);
    }


    @Test
    public void findAll_ShouldReturnAllUser() throws Exception {
        //arrange
        final User userBuilder = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        List<User> datas = new ArrayList();
        datas.add( userBuilder);
        datas.add(userBuilder);
        datas.add( userBuilder);
        datas.add( userBuilder);
        when(userRepository.findAll()).thenReturn(datas);
        //act
        List<User> expected  = userServiceImpl.findAllUsers();
        //assert
        assertEquals(expected,datas);

    }

    @Test
    public void update_ShouldUpdateUser() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        //act
        when(userRepository.save(any(User.class))).thenReturn(user);
        //assert
        final User expected = userServiceImpl.update(user);
        assertEquals(user.getUsername(),expected.getUsername());
    }

    @Test
    public void updatePassword_ShouldUpdatePassword() throws Exception {
        //arrange
        final ChangePasswordDto changePasswordDto = ChangePasswordDtoBuilder.newBuilder().
                username("Apollo").oldPassword("Apollo10").password("Apollo1010").confirmPassword("Apollo1010").build();
        final User user = UserBuilder.newBuilder().username("Apollo").
                password("Apollo10").confirmPassword("Apollo10").email("apollo@gmail.com").isActive(false).build();
        //act
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        final boolean expected = userServiceImpl.updatePassword(changePasswordDto);
        //assert
        verify(userRepository,times(1)).findByUsername(anyString());
        verify(userRepository,times(1)).save(any(User.class));
        assertTrue(expected);

    }

    @Test
    public void updatePassword_ShouldThrowUserException() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        final ChangePasswordDto changePasswordDto = ChangePasswordDtoBuilder.newBuilder().
                username("Puudsa911").oldPassword("Tricolore9").password("Tricolore10").confirmPassword("Tricolore10").build();
        //act
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        assertThrows(UserException.class,() -> {
            userServiceImpl.updatePassword(changePasswordDto);
        });
        //assert
        verify(userRepository,times(1)).findByUsername(anyString());
    }



    @Test
    public void activateUser_ShouldActivateUser() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        //act
        when(userRepository.save(any(User.class))).thenReturn(user);
        userServiceImpl.activateUser(user);
        //assert
        verify(userRepository,times(1)).save(any(User.class));

    }



}
