package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Services.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public String createPost(@RequestParam("content") String content,
                             Authentication authentication) {

        String username = authentication.getName();
        postService.createPost(content, username);

        return "redirect:/";
    }
}