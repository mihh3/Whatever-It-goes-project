package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Dtos.UserRegistrationDto;
import com.luv2code.springboot.demosecurity.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userDto, RedirectAttributes redirectAttributes) {
        userService.save(userDto);
        redirectAttributes.addFlashAttribute("success", "Registration successful! Please log in.");

        return "redirect:/showMyLoginPage";
    }
}
