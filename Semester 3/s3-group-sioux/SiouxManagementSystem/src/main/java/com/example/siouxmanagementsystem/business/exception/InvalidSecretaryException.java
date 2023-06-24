package com.example.siouxmanagementsystem.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidSecretaryException extends ResponseStatusException {
    public InvalidSecretaryException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
