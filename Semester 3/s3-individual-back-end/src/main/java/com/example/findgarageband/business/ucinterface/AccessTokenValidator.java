package com.example.findgarageband.business.ucinterface;

public interface AccessTokenValidator {
    void checkAccessToken(Long userId);
    void checkUserOnlyAccessToken(Long userId);
}
