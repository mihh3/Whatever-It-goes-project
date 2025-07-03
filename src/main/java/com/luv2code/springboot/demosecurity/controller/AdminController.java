package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Dtos.UserLastLoginDto;
import com.luv2code.springboot.demosecurity.Repositories.LoginActivityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final LoginActivityRepository loginActivityRepository;

    public AdminController(LoginActivityRepository loginActivityRepository) {
        this.loginActivityRepository = loginActivityRepository;
    }

    @GetMapping("/last-logins")
    public String showLastLogins(Model model) {
        List<UserLastLoginDto> logins = loginActivityRepository.findLastLoginTimes();
        model.addAttribute("logins", logins);
        return "last-logins";
    }
}
