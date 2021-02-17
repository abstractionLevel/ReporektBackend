package com.example.ReportPlayer.controllers;


import com.example.ReportPlayer.models.user.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/registration/")
@CrossOrigin
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



    @PostMapping()
    public ResponseEntity saveUser(@Valid @RequestBody final UserDto userDto, HttpServletRequest request) {
        final User user = userService.save(userDto);
        final VerificationToken verificationToken = verificationTokenService.save(user);
        final Email email = constructRegisterEmailToken(user,verificationToken,request.getLocalAddr());
        senderService.send(email);
        return new ResponseEntity(HttpStatus.CREATED);
    }



    @GetMapping()
    public ModelAndView activationAccount(final HttpServletRequest request, @RequestParam("token") final String token ,ModelAndView modelAndView) {
        final VerificationToken verificationToken = verificationTokenService.findTokenByConfirmationToken(token);
        String tokenResult = verifyToken.verify(verificationToken);
        if(tokenResult.equals("valid")) {
            modelAndView.setViewName("verify_success");
            userService.activateUser(verificationToken.getUser());
            verificationTokenService.deleteTokenByToken(verificationToken);
            return modelAndView;
        }
        userService.delete(verificationToken.getUser());
        return modelAndView;
    }





    // NON-API

    private Email constructRegisterEmailToken(final User user, final VerificationToken token,String request) {
        Email email = new Email();
        email.setTo(user.getEmail());
        email.setSubject("Registration Confirmation");
        email.setContent("To confirm your account, please click here : "
                + request+":8080/api/v1/registration/?token=" +token.getConfirmationToken());
        return email;

    }



}