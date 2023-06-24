package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.chat.UpdateChatRequest;
import com.example.findgarageband.domain.chat.UpdateChatResponse;

public interface UpdateChatUC {
    UpdateChatResponse leaveChat(UpdateChatRequest request);
    UpdateChatResponse joinChat(UpdateChatRequest request);
}
