package com.example.findgarageband.business.impl.chatimpl;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.ucinterface.UpdateChatUC;
import com.example.findgarageband.domain.chat.UpdateChatRequest;
import com.example.findgarageband.domain.chat.UpdateChatResponse;
import com.example.findgarageband.persistence.ChatRepository;
import com.example.findgarageband.persistence.entity.ChatEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UpdateChatUCImpl implements UpdateChatUC {
    private final ChatRepository chatRepository;
    private final AccessTokenValidator accessTokenValidator;

    @Transactional
    @Override
    public UpdateChatResponse leaveChat(UpdateChatRequest request) {
        accessTokenValidator.checkUserOnlyAccessToken(request.getUserId());

        if (!chatRepository.existsById(request.getId())) {
            throw new InvalidIdException();
        }
        ChatEntity chat = chatRepository.findChatEntityById(request.getId());

        if(request.getUsername().equals(chat.getJoiner())) {
            //leaving chat as a "joiner"
            chat.setJoiner(null);
            chatRepository.save(chat);
            return UpdateChatResponse.builder().response("Joiner left chat").build();
        }
        else if(chat.getStarter().equals(request.getUsername())) {
            //leaving chat as a "starter" / deleting chat
            chatRepository.deleteById(request.getId());
            return UpdateChatResponse.builder().response("Chat finished and deleted").build();
        }
        else
            return UpdateChatResponse.builder().response("Something went wrong").build();
    }

    @Transactional
    @Override
    public UpdateChatResponse joinChat(UpdateChatRequest request) {
        accessTokenValidator.checkUserOnlyAccessToken(request.getUserId());

        if (!chatRepository.existsById(request.getId())) {
            throw new InvalidIdException();
        }
        ChatEntity chat = chatRepository.findChatEntityById(request.getId());

        if(!chat.getStarter().equals(request.getUsername()) && chat.getJoiner() == null) {
            //joining the session/chat
            chat.setJoiner(request.getUsername());
            chatRepository.save(chat);
            return UpdateChatResponse.builder().response("Someone entered chat").build();
        }
        else
            return UpdateChatResponse.builder().response("Not allowed to enter chat").build();
    }
}
