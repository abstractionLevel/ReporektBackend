package com.example.ReportPlayer.controllers;


import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.services.captcha.CaptchaValidator;
import com.example.ReportPlayer.services.user.UserService;
import com.example.ReportPlayer.utils.pojo.Email;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.services.email.EmailSenderService;
import com.example.ReportPlayer.services.token.VerificationTokenService;
import com.example.ReportPlayer.utils.VerifyToken;
import com.example.ReportPlayer.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = { "https://reporekt.com","https://www.reporekt.com","http://localhost:3000","http://localhost:8080"}, maxAge = 3600)
@RequestMapping("/api/v1/registration/")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private VerifyToken verifyToken;

    @Autowired
    private CaptchaValidator captchaValidator;



    @RequestMapping("/provaTemplate")
    public ModelAndView welcome(ModelAndView modelAndView) {
        modelAndView.setViewName("password_updated");
        return modelAndView;
    }


    @PostMapping()
    public ResponseEntity saveUser(@Valid @RequestBody final UserDto userDto, HttpServletRequest request) throws IOException, MessagingException {
        Boolean isValidCaptcha = captchaValidator.validateCaptcha(userDto.getCaptcha());
        if(!isValidCaptcha) {
            System.out.println("Recaptcha not valid");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        final User user = userService.save(userDto);
        final VerificationToken verificationToken = verificationTokenService.save(user);
        final Email email = constructEmailActivateAccount(user,verificationToken);
        senderService.send(email,"confirm_account");
        return new ResponseEntity(HttpStatus.CREATED);
    }



    @GetMapping()
    public ResponseEntity activationAccount(@RequestParam("token") final String token) {
        final VerificationToken verificationToken = verificationTokenService.findTokenByConfirmationToken(token);
        String tokenResult = verifyToken.verify(verificationToken);
        if(tokenResult.equals("valid")) {
            System.out.println("Activation account for:" + verificationToken.getUser().getUsername() + " is valid ");
            userService.activateUser(verificationToken.getUser());
            verificationTokenService.deleteTokenByToken(verificationToken);
            return ResponseEntity.ok().build();
        }
        if(tokenResult.equals("expired")) {
            System.out.println("token expired for " + verificationToken.getUser().getUsername());
            verificationTokenService.deleteTokenByToken(verificationToken);
            userService.delete(verificationToken.getUser());
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return  new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    // NON-API

    private Email constructEmailActivateAccount(final User user, final VerificationToken token) {
        Email mail = new Email();
        mail.setTo(user.getEmail());
        mail.setSubject("Registration Confirmation");

        Map model = new HashMap();
        model.put("name", user.getUsername());
        model.put("token", "https://www.reporekt.com/activate-account/"+token.getConfirmationToken());
        model.put("contactUsUrl","reporekt.com/#/help-center");
        mail.setProps(model);
        return mail;
    }




}