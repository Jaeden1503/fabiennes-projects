package com.example.findgarageband.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidIdException extends ResponseStatusException {
    public InvalidIdException() {
        super(HttpStatus.BAD_REQUEST, "NONE EXIST WITH THIS ID");
    }
}
