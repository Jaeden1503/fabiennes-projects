package com.example.findgarageband.business.impl.chatimpl;

import com.example.findgarageband.business.impl.ChatConverter;
import com.example.findgarageband.business.ucinterface.GetAllChatsUC;
import com.example.findgarageband.domain.chat.Chat;
import com.example.findgarageband.domain.chat.GetAllChatsResponse;
import com.example.findgarageband.persistence.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAllChatsUCImpl implements GetAllChatsUC {
    private final ChatRepository chatRepository;
    @Transactional
    @Override
    public GetAllChatsResponse getAllChats() {
        List<Chat> chats = chatRepository.findChatEntitiesByJoinerNull()
                .stream()
                .map(ChatConverter::convert)
                .toList();

        return GetAllChatsResponse.builder()
                .chats(chats)
                .build();
    }
}
