package com.example.findgarageband.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRequest {
    @NotBlank
    private String starter;

    @NotBlank
    private String title;
}
