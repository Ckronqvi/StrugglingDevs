package com.ckronqvi.website.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.ckronqvi.website.entities.Message;
import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.enums.UserType;
import com.ckronqvi.website.exceptions.ReceiverNotFoundException;
import com.ckronqvi.website.exceptions.UnauthorizedException;
import com.ckronqvi.website.repositories.MessageRepository;
import com.ckronqvi.website.repositories.ProjectInterestRepository;
import com.ckronqvi.website.repositories.UserRepository;

@SpringBootTest(classes = {
        MessageService.class, 
        MessageRepository.class, 
        ProjectInterestRepository.class, 
        UserRepository.class
    })
class MessageServiceTest {
    @SpyBean
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private ProjectInterestRepository projectInterestRepository;

    @MockBean
    private UserRepository userRepository;

    private User developerUser;
    private User recruiterUser;
    private User anotherDeveloper;
    private Message testMessage;

    @BeforeEach
    void setUp() {
        // Initialize test users
        developerUser = User.builder()
                .userId(1L)
                .username("developer")
                .userType(UserType.DEVELOPER)
                .build();

        recruiterUser = User.builder()
                .userId(2L)
                .username("recruiter")
                .userType(UserType.RECRUITER)
                .build();

        anotherDeveloper = User.builder()
                .userId(3L)
                .username("another_developer")
                .userType(UserType.DEVELOPER)
                .build();

        // Initialize test message
        testMessage = Message.builder()
                .messageId(1L)
                .sender(developerUser)
                .receiver(recruiterUser)
                .messageText("Test message")
                .isRead(false)
                .build();
    }

    @Test
    void sendMessage_WhenDeveloperCanMessageRecruiter_ShouldSucceed() {
        // Arrange
        when(userRepository.findByUsername(recruiterUser.getUsername()))
                .thenReturn(Optional.of(recruiterUser));
        when(projectInterestRepository.existsByDeveloperIdAndRecruiterIdAndStatus(
                developerUser.getUserId(),
                recruiterUser.getUserId(),
                "accepted")).thenReturn(true);
        when(messageRepository.save(any(Message.class))).thenReturn(testMessage);

        // Act
        Message result = messageService.sendMessage(
                developerUser,
                recruiterUser.getUsername(),
                "Test message");

        // Assert
        assertNotNull(result);
        assertEquals(testMessage.getMessageId(), result.getMessageId());
        verify(messageRepository).save(any(Message.class));
    }

    @Test
    void sendMessage_WhenDeveloperCannotMessageRecruiter_ShouldThrowUnauthorizedException() {
        // Arrange
        when(userRepository.findByUsername(recruiterUser.getUsername()))
                .thenReturn(Optional.of(recruiterUser));
        when(projectInterestRepository.existsByDeveloperIdAndRecruiterIdAndStatus(
                developerUser.getUserId(),
                recruiterUser.getUserId(),
                "accepted")).thenReturn(false);

        // Act & Assert
        assertThrows(UnauthorizedException.class, () -> messageService.sendMessage(
                developerUser,
                recruiterUser.getUsername(),
                "Test message"));
        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void sendMessage_WhenReceiverNotFound_ShouldThrowReceiverNotFoundException() {
        // Arrange
        when(userRepository.findByUsername(recruiterUser.getUsername()))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ReceiverNotFoundException.class, () -> messageService.sendMessage(
                developerUser,
                recruiterUser.getUsername(),
                "Test message"));
        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void sendMessage_BetweenSameUserTypes_ShouldThrowUnauthorizedException() {
        // Arrange
        when(userRepository.findByUsername(anotherDeveloper.getUsername()))
                .thenReturn(Optional.of(anotherDeveloper));

        // Act & Assert
        assertThrows(UnauthorizedException.class, () -> messageService.sendMessage(
                developerUser,
                anotherDeveloper.getUsername(),
                "Test message"));
        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void getConversation_WhenAuthorized_ShouldReturnMessages() {
        // Arrange
        List<Message> expectedMessages = Collections.singletonList(testMessage);
        when(userRepository.findByUsername(recruiterUser.getUsername()))
                .thenReturn(Optional.of(recruiterUser));
        when(projectInterestRepository.existsByDeveloperIdAndRecruiterIdAndStatus(
                developerUser.getUserId(),
                recruiterUser.getUserId(),
                "accepted")).thenReturn(true);
        when(messageRepository.findBySender_UserIdAndReceiver_UserId(
                developerUser.getUserId(),
                recruiterUser.getUserId())).thenReturn(expectedMessages);

        // Act
        List<Message> result = messageService.getConversation(
                developerUser,
                recruiterUser.getUsername());

        // Assert
        assertNotNull(result);
        assertEquals(expectedMessages.size(), result.size());
        assertEquals(expectedMessages.get(0).getMessageId(), result.get(0).getMessageId());
    }

    @Test
    void getConversation_WhenUnauthorized_ShouldThrowUnauthorizedException() {
        // Arrange
        when(userRepository.findByUsername(recruiterUser.getUsername()))
                .thenReturn(Optional.of(recruiterUser));
        when(projectInterestRepository.existsByDeveloperIdAndRecruiterIdAndStatus(
                any(), any(), anyString())).thenReturn(false);

        // Act & Assert
        assertThrows(UnauthorizedException.class, () -> messageService.getConversation(
                developerUser,
                recruiterUser.getUsername()));
        verify(messageRepository, never())
                .findBySender_UserIdAndReceiver_UserId(any(), any());
    }

    @Test
    void getConversation_WhenReceiverNotFound_ShouldThrowReceiverNotFoundException() {
        // Arrange
        when(userRepository.findByUsername(recruiterUser.getUsername()))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ReceiverNotFoundException.class, () -> messageService.getConversation(
                developerUser,
                recruiterUser.getUsername()));
        verify(messageRepository, never())
                .findBySender_UserIdAndReceiver_UserId(any(), any());
    }
}