package com.example.siouxmanagementsystem.business.UseCases.LoginUseCases;

import com.example.siouxmanagementsystem.domain.Login.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
