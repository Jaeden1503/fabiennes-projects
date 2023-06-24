package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.chat.CreateChatRequest;
import com.example.findgarageband.domain.chat.CreateChatResponse;

public interface CreateChatUC {
    CreateChatResponse createChat(CreateChatRequest request);
}
