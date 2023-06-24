package com.example.findgarageband.domain.chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateChatResponse {
    private Long chatId;
}
