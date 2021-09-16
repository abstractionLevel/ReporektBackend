package com.example.ReportPlayer.controllers;


import com.example.ReportPlayer.dto.request.ContactUsDto;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.services.captcha.CaptchaValidator;
import com.example.ReportPlayer.services.helpCenter.HelpCenterService;
import com.example.ReportPlayer.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/help-center")
public class HelpCenterController {

    @Autowired
    private HelpCenterService helpCenterService;
    @Autowired
    private UserService  userService;
    @Autowired
    private CaptchaValidator captchaValidator;

    @PostMapping(value = "/contact-us")
    public ResponseEntity save(@Valid  @RequestBody ContactUsDto contactUsDto) {
        Boolean isValidCaptcha = captchaValidator.validateCaptcha(contactUsDto.getCaptcha());
        if(!isValidCaptcha) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        final CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        contactUsDto.setUser(user.getEmail());
        helpCenterService.contactUs(contactUsDto);
        return ResponseEntity.ok().build();
    }

}
