package com.luv2code.springboot.demosecurity.Services;

import com.luv2code.springboot.demosecurity.Entity.ChatMessageEntity;
import com.luv2code.springboot.demosecurity.Repositories.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ChatService {

    private final ChatMessageRepository repository;

    public ChatService(ChatMessageRepository repository){
        this.repository = repository;
    }

    public void saveMessage(String sender,
                            String receiver,
                            String content) {

        ChatMessageEntity message = new ChatMessageEntity();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        repository.save(message);
    }

    public List<ChatMessageEntity> getConversation(
            String user1,
            String user2) {

        return repository
                .findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampAsc(
                        user1, user2,
                        user2, user1
                );
    }
}