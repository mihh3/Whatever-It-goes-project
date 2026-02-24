package com.luv2code.springboot.demosecurity.controller;


import com.luv2code.springboot.demosecurity.Services.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    private final PostService postService;

    public DemoController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String showHome(Model model,
                           @PageableDefault(size = 10) Pageable pageable) {

        model.addAttribute("posts",
                postService.getFeed(pageable));

        return "home";
    }

    @GetMapping("/leaders")
    public String showLeaders() {
        return "leaders";
    }

    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }
}