package com.example.findgarageband.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    private Long id;
    private String starter;
    private String joiner;
    private String title;
}
