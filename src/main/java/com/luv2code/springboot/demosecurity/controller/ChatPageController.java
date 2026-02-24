package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Repositories.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatPageController {

    private final MemberRepository memberRepository;

    public ChatPageController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/chat")
    public String showChatPage(Model model, Authentication authentication) {

        model.addAttribute("currentUser", authentication.getName());
        model.addAttribute("users", memberRepository.findAll());

        return "chat";
    }
}
