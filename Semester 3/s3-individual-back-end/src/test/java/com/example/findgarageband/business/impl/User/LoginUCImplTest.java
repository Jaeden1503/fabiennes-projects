package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.exception.InvalidCredentialsException;
import com.example.findgarageband.business.impl.AccessTokenEncoderDecoderImpl;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.LoginRequest;
import com.example.findgarageband.domain.LoginResponse;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.UserEntity;
import com.example.findgarageband.persistence.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUCImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder;
    @InjectMocks
    private LoginUCImpl loginUC;

    @Test
    void login_shouldLoginCorrectlyAndReturnAccessToken() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("fabienne@gmail.com")
                .password("very_secure_password")
                .build();

        UserEntity user = createDummyUser();

        AccessToken accessToken = AccessToken.builder()
                .subject(user.getUsername())
                .roles(List.of("USER"))
                .userId(1L)
                .build();

        when(userRepositoryMock.findByEmail(loginRequest.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(accessTokenEncoderDecoder.encode(accessToken)).thenReturn("generated_accessToken");

        LoginResponse actualResponse = loginUC.login(loginRequest);
        LoginResponse expectedResponse = LoginResponse.builder()
                .accessToken("generated_accessToken")
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(userRepositoryMock).findByEmail(loginRequest.getEmail());
    }

    @Test
    void login_shouldNotLoginWithWrongCredentials() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("peter@gmail.com")
                .password("peters_password")
                .build();

        when(userRepositoryMock.findByEmail(loginRequest.getEmail())).thenReturn(null);

        assertThrows(InvalidCredentialsException.class, () -> {
            loginUC.login(loginRequest);
        });
    }

    @Test
    void login_shouldNotLoginWithWrongPassword() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("fabienne@gmail.com")
                .password("wrong_password")
                .build();

        UserEntity user = createDummyUser();

        when(userRepositoryMock.findByEmail(loginRequest.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> {
            loginUC.login(loginRequest);
        });
    }

    private UserEntity createDummyUser() {
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

        return user;
    }
}