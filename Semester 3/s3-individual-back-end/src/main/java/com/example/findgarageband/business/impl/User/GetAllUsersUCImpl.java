package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.ucinterface.GetAllUsersUC;
import com.example.findgarageband.business.impl.UserConverter;
import com.example.findgarageband.domain.User.GetAllUsersResponse;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAllUsersUCImpl implements GetAllUsersUC {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public GetAllUsersResponse getAllUsers() {
        List<User> users = userRepository.findAllNormalUsers()
                .stream()
                .map(UserConverter::convert)
                .toList();

        return GetAllUsersResponse.builder()
                .users(users)
                .build();
    }
}
