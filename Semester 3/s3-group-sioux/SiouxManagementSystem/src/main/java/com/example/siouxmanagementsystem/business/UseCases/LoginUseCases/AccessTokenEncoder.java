package com.example.siouxmanagementsystem.business.UseCases.LoginUseCases;

import com.example.siouxmanagementsystem.domain.Login.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
