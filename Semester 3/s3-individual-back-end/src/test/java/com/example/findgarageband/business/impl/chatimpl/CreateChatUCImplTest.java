package com.example.findgarageband.business.impl.chatimpl;

import com.example.findgarageband.business.exception.InvalidChatCreationException;
import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.chat.CreateChatRequest;
import com.example.findgarageband.domain.chat.CreateChatResponse;
import com.example.findgarageband.persistence.ChatRepository;
import com.example.findgarageband.persistence.entity.ChatEntity;
import com.example.findgarageband.persistence.entity.RoleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateChatUCImplTest {
    @Mock
    private ChatRepository chatRepositoryMock;
    @Mock
    private AccessToken accessToken;
    @InjectMocks
    private CreateChatUCImpl createChatUC;

    @Test
    void createChat_shouldSaveChat() {
        ChatEntity newChat = ChatEntity.builder()
                .starter("fabienne")
                .title("new chat")
                .build();

        ChatEntity returnedChat = ChatEntity.builder()
                .id(1L)
                .starter("fabienne")
                .title("new chat")
                .build();

        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(true);
        when(chatRepositoryMock.findChatEntityByStarter(newChat.getStarter())).thenReturn(Optional.empty());
        when(chatRepositoryMock.save(newChat)).thenReturn(returnedChat);

        CreateChatRequest request = CreateChatRequest.builder()
                .starter("fabienne")
                .title("new chat")
                .build();

        CreateChatResponse actualResponse = createChatUC.createChat(request);

        CreateChatResponse expectedResponse = CreateChatResponse.builder()
                .chatId(1L)
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(chatRepositoryMock).save(newChat);
    }

    @Test
    void createChat_shouldNotSaveChatDueToInvalidAccess() {
        CreateChatRequest request = CreateChatRequest.builder()
                .starter("fabienne")
                .title("new chat")
                .build();

        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(false);

        assertThrows(UnauthorizedDataAccessException.class, () -> {
            createChatUC.createChat(request);
        });
    }

    @Test
    void createChat_shouldNotSaveChatBecauseUserAlreadyHasAChat() {
        CreateChatRequest request = CreateChatRequest.builder()
                .starter("fabienne")
                .title("new chat")
                .build();

        ChatEntity returnedChat = ChatEntity.builder()
                .id(1L)
                .starter("fabienne")
                .title("new chat")
                .build();

        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(true);
        when(chatRepositoryMock.findChatEntityByStarter(request.getStarter())).thenReturn(Optional.of(returnedChat));

        assertThrows(InvalidChatCreationException.class, () -> {
            createChatUC.createChat(request);
        });
    }
}