package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
