package com.example.findgarageband.domain.chat;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllChatsResponse {
    private List<Chat> chats;
}
