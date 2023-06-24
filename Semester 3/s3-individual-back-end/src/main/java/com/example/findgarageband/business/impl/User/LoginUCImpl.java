package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.ucinterface.AccessTokenEncoder;
import com.example.findgarageband.business.ucinterface.LoginUC;
import com.example.findgarageband.business.exception.InvalidCredentialsException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.LoginRequest;
import com.example.findgarageband.domain.LoginResponse;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginUCImpl implements LoginUC {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        //wrong login credentials
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        //wrong password
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
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .userId(user.getId())
                        .build());
    }
}
