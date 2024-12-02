package com.ckronqvi.website.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ckronqvi.website.entities.Message;
import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.exceptions.ReceiverNotFoundException;
import com.ckronqvi.website.exceptions.SenderNotFoundException;
import com.ckronqvi.website.exceptions.UnauthorizedException;
import com.ckronqvi.website.repositories.MessageRepository;
import com.ckronqvi.website.repositories.ProjectInterestRepository;
import com.ckronqvi.website.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MessageService {
    
    private final MessageRepository messageRepository;
    
    private final ProjectInterestRepository projectInterestRepository;

    private final UserRepository userRepository;
    
    /**
     * Checks if a developer is allowed to message a recruiter
     */
    private boolean canMessageRecruiter(Long developerId, Long recruiterId) {
        // Check if there's any project where the developer has been accepted by this recruiter
        return projectInterestRepository.existsByDeveloperIdAndRecruiterIdAndStatus(
            developerId, 
            recruiterId, 
            "accepted"
        );
    }
    
    /**
     * Sends a message if allowed
     */
    public Message sendMessage(User sender, String receiver, String messageText) 
        throws UnauthorizedException, SenderNotFoundException, ReceiverNotFoundException {
        
        User receiverUser = userRepository.findByUsername(receiver)
            .orElseThrow(() -> new ReceiverNotFoundException("Receiver not found"));
            
        // If sender is developer and receiver is recruiter, check permissions
        if (sender.getUserType().name().equalsIgnoreCase("DEVELOPER") && 
            receiverUser.getUserType().name().equalsIgnoreCase("RECRUITER")) {
            
            if (!canMessageRecruiter(sender.getUserId(), receiverUser.getUserId())) {
                throw new UnauthorizedException(
                    "You can only message recruiters who have accepted you for a project"
                );
            }
        }
        
        // If sender is recruiter and receiver is developer, check permissions
        if (sender.getUserType().name().equalsIgnoreCase("recruiter") && 
            receiverUser.getUserType().name().equalsIgnoreCase("developer")) {
            
            if (!canMessageRecruiter(sender.getUserId(), receiverUser.getUserId())) {
                throw new UnauthorizedException(
                    "You can only message developers you have accepted for a project"
                );
            }
        }

        if(sender.getUserType().equals(receiverUser.getUserType())) {
            throw new UnauthorizedException("Messages are only allowed between developers and recruiters");
        }
        
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiverUser);
        message.setMessageText(messageText);
        message.setRead(false);
        
        return messageRepository.save(message);
    }
    
    /**
     * Gets conversation history between two users
     */
    public List<Message> getConversation(User user, String receiver) {
        User user2 = userRepository.findByUsername(receiver)
            .orElseThrow(() -> new ReceiverNotFoundException("Receiver not found"));
        if (!canMessageRecruiter(user.getUserId(), user2.getUserId()) && 
            !canMessageRecruiter(user2.getUserId(), user.getUserId())) {
            throw new UnauthorizedException("Unauthorized to view this conversation");
        }
        
        return messageRepository.findBySender_UserIdAndReceiver_UserId(user.getUserId(), user2.getUserId());
    }
}