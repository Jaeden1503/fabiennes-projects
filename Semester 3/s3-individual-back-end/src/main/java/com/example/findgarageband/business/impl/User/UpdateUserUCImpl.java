package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.ucinterface.UpdateUserUC;
import com.example.findgarageband.business.exception.InvalidSearchPostException;
import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.business.exception.UsernameAlreadyExistsException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.User.UpdateUserRequest;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUCImpl implements UpdateUserUC {
    private final UserRepository userRepository;
    private AccessToken requestAccessToken;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void updateUser(UpdateUserRequest request) {
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name())) {
            if (!requestAccessToken.hasRole(RoleEnum.USER.name()) || !requestAccessToken.getUserId().equals(request.getId())) {
                throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }

        Optional<UserEntity> optionalUserEntity = userRepository.findById(request.getId());
        if(optionalUserEntity.isEmpty()) {
            throw new InvalidIdException();
        }

        UserEntity user = optionalUserEntity.get(); //existing user
        if(!user.getUsername().equals(request.getUsername())) {
            if(userRepository.existsByUsername(request.getUsername())) {
                throw new UsernameAlreadyExistsException();
            }
        }

        String encodedPassword;
        if (request.getPassword() != null) {
            encodedPassword = passwordEncoder.encode(request.getPassword());
        }
        else {
            encodedPassword = user.getPassword();
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setAbout(request.getAbout());

        userRepository.save(user);
    }
}
