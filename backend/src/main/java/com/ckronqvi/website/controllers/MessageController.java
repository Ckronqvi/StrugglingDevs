package com.ckronqvi.website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ckronqvi.website.Annotations.CurrentUser;
import com.ckronqvi.website.DTOs.MessageRequestDTO;
import com.ckronqvi.website.entities.Message;
import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.services.MessageService;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    
    @PostMapping
    public ResponseEntity<Message> sendMessage(
        @CurrentUser User currentUser,
        @RequestBody MessageRequestDTO request
    ) {
        Message message = messageService.sendMessage(
            currentUser,
            request.username(),
            request.message()
        );
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/conversation/{username}")
    public ResponseEntity<List<Message>> getConversation(
        @PathVariable String username,
        @CurrentUser User currentUser
    ) {
        List<Message> messages = messageService.getConversation(currentUser, username);
        return ResponseEntity.ok(messages);
    }
}