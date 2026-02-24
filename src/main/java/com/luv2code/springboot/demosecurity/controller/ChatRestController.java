package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Entity.ChatMessageEntity;
import com.luv2code.springboot.demosecurity.Services.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/chat")
public class ChatRestController {
    private final ChatService chatService;

    public ChatRestController(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/history/{username}")
    public List<ChatMessageEntity> getChatHistory(
            @PathVariable String username,
            Principal principal) {

        return chatService.getConversation(
                principal.getName(),
                username
        );
    }
}
