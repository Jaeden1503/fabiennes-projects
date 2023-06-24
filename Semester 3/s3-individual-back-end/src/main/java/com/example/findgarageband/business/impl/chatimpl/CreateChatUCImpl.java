package com.example.findgarageband.business.impl.chatimpl;

import com.example.findgarageband.business.exception.InvalidChatCreationException;
import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.business.ucinterface.CreateChatUC;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.chat.CreateChatRequest;
import com.example.findgarageband.domain.chat.CreateChatResponse;
import com.example.findgarageband.persistence.ChatRepository;
import com.example.findgarageband.persistence.entity.ChatEntity;
import com.example.findgarageband.persistence.entity.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateChatUCImpl implements CreateChatUC {
    private final ChatRepository chatRepository;
    private AccessToken requestAccessToken;

    @Transactional
    @Override
    public CreateChatResponse createChat(CreateChatRequest request) {
        if (!requestAccessToken.hasRole(RoleEnum.USER.name())) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        Optional<ChatEntity> optionalChat = chatRepository.findChatEntityByStarter(request.getStarter());
        if(optionalChat.isPresent()) {
            throw new InvalidChatCreationException();
        }

        ChatEntity newChat = ChatEntity.builder()
                .starter(request.getStarter())
                .title(request.getTitle())
                .build();

        ChatEntity savedChat = chatRepository.save(newChat);

        return CreateChatResponse.builder().chatId(savedChat.getId()).build();
    }
}
