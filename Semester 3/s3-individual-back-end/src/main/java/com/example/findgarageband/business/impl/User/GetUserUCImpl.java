package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.ucinterface.GetUserUC;
import com.example.findgarageband.business.exception.InvalidSearchPostException;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class GetUserUCImpl implements GetUserUC {
    private final UserRepository userRepository;
    private final AccessTokenValidator accessTokenValidator;

    @Transactional
    @Override
    public User getUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new InvalidIdException();
        }

        UserEntity userEntity = userRepository.findUserEntityById(id);

        accessTokenValidator.checkAccessToken(userEntity.getId());

        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .about(userEntity.getAbout())
                .build();
    }
}
