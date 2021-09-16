package com.example.ReportPlayer.controllers;



import com.example.ReportPlayer.dto.email.EmailDto;
import com.example.ReportPlayer.dto.password.ForgotPasswordDto;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.services.captcha.CaptchaValidator;
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

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = { "https://reporekt.com","https://www.reporekt.com","http://localhost:3000"}, maxAge = 3600)
@RequestMapping("/api/v1/users")
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
    @Autowired
    private CaptchaValidator captchaValidator;


    @PostMapping(path = "/accounts/update_password")
    public ResponseEntity updatePassword(@RequestBody @Valid final ChangePasswordDto changePasswordDto) {
        final CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findByUsername(userDetails.getUsername());
        changePasswordDto.setUsername(user.getUsername());
        if(!userService.updatePassword(changePasswordDto)) {
            System.out.println("not update password for: " + user.getUsername());
            return ResponseEntity.noContent().build();
        }
        System.out.println("password update for:" + user.getUsername());
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
    public ResponseEntity sendEmailResetPasswordConfirmationToken(HttpServletRequest request, @Valid @RequestBody final EmailDto emailDto) throws IOException, MessagingException {
        final User user = userService.findByEmail(emailDto.getEmail());
        Boolean isValidCaptcha = captchaValidator.validateCaptcha(emailDto.getCaptcha());
        if(!isValidCaptcha) {
            System.out.println("token captcha not valid for: " + user.getUsername());
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if(user!=null) {
            final VerificationToken verificationToken = verificationTokenService.save(user);
            final String appUrl =  request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            final Email email = cnstructResetTokenEmail(user , verificationToken.getConfirmationToken());
            emailSenderService.send(email,"update_password");
            System.out.println("send token confirmation email to: " + user.getUsername());
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();

    }

    @PostMapping(path = "/accounts/update_forgot_password")
    public ResponseEntity updateForgotPassword(@Valid @RequestBody final ForgotPasswordDto forgotPasswordDto ) throws IOException, MessagingException {
        final VerificationToken verificationToken = verificationTokenService.findTokenByConfirmationToken(forgotPasswordDto.getToken());
        if(verifyToken.verify(verificationToken).equals("valid")) {
            System.out.println("email update for user: " + verificationToken.getUser());
            final User user = userService.findByUsername(verificationToken.getUser().getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(forgotPasswordDto.getPassword()));
            userService.update(user);
            verificationTokenService.deleteTokenByToken(verificationToken);
            final Email email = constructEmailSuccessfulUpdatePassword(user);
            emailSenderService.send(email,"password_updated");
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // NON-API


    private Email cnstructResetTokenEmail(final User user, final String token) {
        Email mail = new Email();
        mail.setFrom("reporekt");//era qui il problema del nome
        mail.setTo(user.getEmail());
        mail.setSubject("Reset Password");

        Map model = new HashMap();
        model.put("name", user.getUsername());
        model.put("url", "https://www.reporekt.com/forgotPassword/"+token);
        model.put("contactUsUrl","reporekt.com/#/help-center");
        mail.setProps(model);

        System.out.println(mail.getFrom());
        System.out.println(mail.getTo());
        return mail;
    }

    private Email constructEmailSuccessfulUpdatePassword(User user) {
        Email mail = new Email();
        mail.setTo(user.getEmail());
        mail.setSubject("Password Reset");

        Map model = new HashMap();
        model.put("contactUsUrl","reporekt.com/#/help-center");
        mail.setProps(model);
        return mail;

    }



}