package com.example.findgarageband.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidSearchPostException extends ResponseStatusException {
    public InvalidSearchPostException() {
        super(HttpStatus.BAD_REQUEST, "COULDN'T FIND THE POST");
    }
}
