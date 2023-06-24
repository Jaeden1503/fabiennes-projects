package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.ucinterface.CreateUserUC;
import com.example.findgarageband.business.exception.UsernameAlreadyExistsException;

import com.example.findgarageband.domain.User.CreateUserRequest;
import com.example.findgarageband.domain.User.CreateUserResponse;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.UserEntity;
import com.example.findgarageband.persistence.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CreateUserUCImpl implements CreateUserUC {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        newUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(newUser)
                        .role(RoleEnum.USER)
                        .build()
        ));

        UserEntity savedUser = userRepository.save(newUser);

        return CreateUserResponse.builder().userId(savedUser.getId()).build();
    }
}
