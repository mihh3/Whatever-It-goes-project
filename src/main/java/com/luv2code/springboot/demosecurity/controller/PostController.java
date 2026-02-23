package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Entity.Post;
import com.luv2code.springboot.demosecurity.Services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // =============================
    // SHOW FEED (INSIDE HOME PAGE)
    // =============================
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String showFeed(Model model,
                           @PageableDefault(size = 10) Pageable pageable) {

        Page<Post> posts = postService.getFeed(pageable);
        model.addAttribute("posts", posts);

        return "home";
    }

    // =============================
    // CREATE POST
    // =============================
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/posts")
    public String createPost(@RequestParam("content") String content,
                             Authentication authentication) {

        String username = authentication.getName();
        postService.createPost(content, username);

        return "redirect:/";
    }
}