package com.example.ReportPlayer.controllers;



import com.example.ReportPlayer.dto.email.EmailDto;
import com.example.ReportPlayer.dto.password.ForgotPasswordDto;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.services.email.EmailSenderService;
import com.example.ReportPlayer.services.token.VerificationTokenService;
import com.example.ReportPlayer.services.user.UserService;
import com.example.ReportPlayer.dto.password.ChangePasswordDto;
import com.example.ReportPlayer.utils.VerifyToken;
import com.example.ReportPlayer.utils.pojo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private EmailSenderService senderService;
    @Autowired
    private VerifyToken verifyToken;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping(path = "/accounts/update_password")
    public ResponseEntity updatePassword(@RequestBody @Valid final ChangePasswordDto changePasswordDto) {
        final CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findByUsername(userDetails.getUsername());
        changePasswordDto.setUsername(user.getUsername());
        if(!userService.updatePassword(changePasswordDto)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/username/{username}")
    public ResponseEntity userAlreadyExist(@PathVariable("username") String username) {
        final User user = userService.findByUsername(username);
        if(user==null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity emailAlreadyExist(@PathVariable("email") String email) {
        final User user = userService.findByEmail(email);
        if(user==null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/accounts/email/email_token")
    public ResponseEntity sendEmailResetPasswordConfirmationToken(HttpServletRequest request, @Valid @RequestBody final EmailDto emailDto) {
        final User user = userService.findByEmail(emailDto.getEmail());
        if(user!=null) {
            final VerificationToken verificationToken = verificationTokenService.save(user);
            final String appUrl =  request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            final Email email = constructResetTokenEmail(appUrl,request.getLocale(),verificationToken.getConfirmationToken(),user);
            emailSenderService.send(email);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();

    }

    @PostMapping(path = "/accounts/update_forgot_password")
    public ResponseEntity updateForgotPassword(@Valid @RequestBody final ForgotPasswordDto forgotPasswordDto ) {
        final VerificationToken verificationToken = verificationTokenService.findTokenByConfirmationToken(forgotPasswordDto.getToken());
        if(verifyToken.verify(verificationToken).equals("valid")) {
            final User user = userService.findByUsername(verificationToken.getUser().getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(forgotPasswordDto.getPassword()));
            userService.update(user);
            verificationTokenService.deleteTokenByToken(verificationToken);
            final Email email = constructEmailSuccessfulUpdatePassword(user.getEmail());
            emailSenderService.send(email);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // NON-API

    private Email constructForgotUsernameEmail(final String emailAddress, final String username) {
        Email email = new Email();
        email.setTo(emailAddress);
        email.setSubject("Username");
        email.setContent("You username: " + username);
        return email;
    }

    private Email constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = "http://localhost:3000/forgotPassword/"+token;
        //final String message = messages.getMessage("dio porco",null,locale);
        Email email = new Email();
        email.setTo(user.getEmail());
        email.setSubject("Reset Password");
        email.setContent("dio porco ecco il tuo dio can di link" + "\r\n" + url );
        return email;
    }

    private Email constructEmailSuccessfulUpdatePassword(String emailUser) {
        Email email = new Email();
        email.setTo(emailUser);
        email.setSubject("Password Updated");
        email.setContent("password updated" );
        return email;

    }


}