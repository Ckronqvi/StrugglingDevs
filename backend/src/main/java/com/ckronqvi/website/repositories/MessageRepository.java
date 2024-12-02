package com.ckronqvi.website.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ckronqvi.website.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender_UserIdAndReceiver_UserId(Long senderId, Long receiverId);
}
