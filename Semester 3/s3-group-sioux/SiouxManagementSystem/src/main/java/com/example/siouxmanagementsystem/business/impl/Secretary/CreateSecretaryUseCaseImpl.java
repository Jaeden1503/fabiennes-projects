package com.example.siouxmanagementsystem.business.impl.Secretary;

import com.example.siouxmanagementsystem.business.UseCases.SecretaryUseCases.CreateSecretaryUseCase;
import com.example.siouxmanagementsystem.business.exception.NameAlreadyExistsException;
import com.example.siouxmanagementsystem.domain.Secretary.CreateSecretaryRequest;
import com.example.siouxmanagementsystem.domain.Secretary.CreateSecretaryResponse;
import com.example.siouxmanagementsystem.persistence.SecretaryRepository;
import com.example.siouxmanagementsystem.persistence.UserRepository;
import com.example.siouxmanagementsystem.persistence.entity.RoleEnum;
import com.example.siouxmanagementsystem.persistence.entity.SecretaryEntity;
import com.example.siouxmanagementsystem.persistence.entity.UserEntity;
import com.example.siouxmanagementsystem.persistence.entity.UserRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreateSecretaryUseCaseImpl implements CreateSecretaryUseCase {
    private static final String USERNAME_SUFFIX = "@sioux.nl";
    private final SecretaryRepository secretaryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CreateSecretaryResponse createSecretary(CreateSecretaryRequest request) {
        if (secretaryRepository.existsByName(request.getName())) {
            throw new NameAlreadyExistsException();
        }

        SecretaryEntity savedSecretary = saveNewSecretary(request);

        saveNewUser(savedSecretary, request.getPassword());

        return CreateSecretaryResponse.builder().secretaryId(savedSecretary.getId()).build();
    }

    private void saveNewUser(SecretaryEntity secretary, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        UserEntity newUser = UserEntity.builder()
                .username(secretary.getName() + USERNAME_SUFFIX)
                .password(encodedPassword)
                .secretary(secretary)
                .build();

        newUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .User(newUser)
                        .role(RoleEnum.SECRETARY)
                        .build()));
        userRepository.save(newUser);
    }
    private SecretaryEntity saveNewSecretary(CreateSecretaryRequest request) {
        SecretaryEntity newSecretary = SecretaryEntity.builder()
                .name(request.getName())
                .build();
        return secretaryRepository.save(newSecretary);
    }
}
