package com.example.findgarageband.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidChatCreationException extends ResponseStatusException {
    public InvalidChatCreationException() {
        super(HttpStatus.BAD_REQUEST, "YOU'RE ONLY ALLOWED TO CREATE ONE CHAT AT A TIME");
    }
}
