package com.example.findgarageband.persistence;

import com.example.findgarageband.persistence.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    ChatEntity findChatEntityById(Long chatId);
    Optional<ChatEntity> findChatEntityByStarter(String starter);
    List<ChatEntity> findChatEntitiesByJoinerNull();
}
