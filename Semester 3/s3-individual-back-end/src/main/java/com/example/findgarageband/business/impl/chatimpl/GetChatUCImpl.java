package com.example.findgarageband.business.impl.chatimpl;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.ucinterface.GetChatUC;
import com.example.findgarageband.domain.chat.Chat;
import com.example.findgarageband.persistence.ChatRepository;
import com.example.findgarageband.persistence.entity.ChatEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class GetChatUCImpl implements GetChatUC {
    private final ChatRepository chatRepository;

    @Transactional
    @Override
    public Chat getChat(Long chatId) {
        if (!chatRepository.existsById(chatId)) {
            throw new InvalidIdException();
        }

        ChatEntity chatEntity = chatRepository.findChatEntityById(chatId);

        return Chat.builder()
                .id(chatEntity.getId())
                .starter(chatEntity.getStarter())
                .joiner(chatEntity.getJoiner())
                .title(chatEntity.getTitle())
                .build();
    }
}
