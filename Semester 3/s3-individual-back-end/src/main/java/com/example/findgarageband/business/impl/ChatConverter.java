package com.example.findgarageband.business.impl;

import com.example.findgarageband.domain.chat.Chat;
import com.example.findgarageband.persistence.entity.ChatEntity;

public final class ChatConverter {
    private ChatConverter() {

    }

    //converts from chat to chat entity
    public static Chat convert(ChatEntity chatEntity) {
        return Chat.builder()
                .id(chatEntity.getId())
                .starter(chatEntity.getStarter())
                .joiner(chatEntity.getJoiner())
                .title(chatEntity.getTitle())
                .build();
    }
}
