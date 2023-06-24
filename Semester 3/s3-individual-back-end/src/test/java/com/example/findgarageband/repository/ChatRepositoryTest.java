package com.example.findgarageband.repository;

import com.example.findgarageband.persistence.ChatRepository;
import com.example.findgarageband.persistence.entity.ChatEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChatRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ChatRepository chatRepository;

    @Test
    void save_shouldSaveChatWithFields() {
        ChatEntity chatEntity = ChatEntity.builder()
                .starter("fabienne")
                .title("new chat")
                .build();

        ChatEntity savedChat = chatRepository.save(chatEntity);
        assertNotNull(savedChat.getId());

        savedChat = entityManager.find(ChatEntity.class, savedChat.getId());

        ChatEntity expectedChat = ChatEntity.builder()
                .id(savedChat.getId())
                .starter("fabienne")
                .title("new chat")
                .build();

        assertEquals(expectedChat, savedChat);
    }
}
