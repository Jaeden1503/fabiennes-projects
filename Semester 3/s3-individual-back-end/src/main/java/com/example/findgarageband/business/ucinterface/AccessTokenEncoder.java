package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
