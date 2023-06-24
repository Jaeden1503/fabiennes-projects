package com.example.findgarageband.business.impl;

import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.UserEntity;
import com.example.findgarageband.persistence.entity.UserRoleEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccessTokenEncoderDecoderImplTest {
    @Mock
    private Key key;
    @Mock
    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoderMock;

    @Test
    void encodeAccessToken_shouldReturnStringToken() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password("very_secure_password")
                .build();

        user.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .id(1L)
                        .user(user)
                        .role(RoleEnum.USER)
                        .build()
        ));

        AccessToken accessToken = AccessToken.builder()
                .subject(user.getUsername())
                .roles(List.of("USER"))
                .userId(1L)
                .build();


        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("TEST158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFTEST"));

        Map<String, Object> claimsMap = new HashMap<>();
        Instant now = Instant.now();
        String returnString = Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();

        when(accessTokenEncoderDecoderMock.encode(accessToken)).thenReturn(returnString);

        String actualAccessToken = accessTokenEncoderDecoderMock.encode(accessToken);
        String expectedAccessToken = Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();

        assertEquals(expectedAccessToken, actualAccessToken);
        verify(accessTokenEncoderDecoderMock).encode(accessToken);
    }
}