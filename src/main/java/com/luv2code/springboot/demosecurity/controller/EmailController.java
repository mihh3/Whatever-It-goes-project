package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("sendEmail")
    public String sendEmail(){
        emailService.sendEmail("michailanastasiadis36@gmail.com","Test Body","Test Subject");
        return "Sent Successfully" ;
    }
}
