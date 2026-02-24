package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Dtos.ChatMessage;
import com.luv2code.springboot.demosecurity.Services.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    public ChatController(SimpMessagingTemplate messagingTemplate,ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message,
                            Principal principal) {

        String senderUsername = principal.getName();

        chatService.saveMessage(senderUsername,message.getSender(), message.getContent());


        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/messages",
                new ChatMessage(senderUsername, message.getContent())
        );
    }
}