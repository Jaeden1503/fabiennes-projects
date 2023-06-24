package com.example.siouxmanagementsystem.business.impl.Login;

import com.example.siouxmanagementsystem.business.UseCases.LoginUseCases.AccessTokenEncoder;
import com.example.siouxmanagementsystem.business.UseCases.LoginUseCases.LoginUseCase;
import com.example.siouxmanagementsystem.business.exception.InvalidCredentialsException;
import com.example.siouxmanagementsystem.domain.Login.AccessToken;
import com.example.siouxmanagementsystem.domain.Login.LoginRequest;
import com.example.siouxmanagementsystem.domain.Login.LoginResponse;
import com.example.siouxmanagementsystem.persistence.UserRepository;
import com.example.siouxmanagementsystem.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        Long secretaryId = user.getSecretary() != null ? user.getSecretary().getId() : null;
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .secretaryId(secretaryId)
                        .build());
    }

}
