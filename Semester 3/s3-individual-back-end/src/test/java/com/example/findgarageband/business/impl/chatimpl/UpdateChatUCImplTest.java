package com.example.findgarageband.business.impl.chatimpl;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.domain.chat.UpdateChatRequest;
import com.example.findgarageband.domain.chat.UpdateChatResponse;
import com.example.findgarageband.persistence.ChatRepository;
import com.example.findgarageband.persistence.entity.ChatEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateChatUCImplTest {
    @Mock
    private ChatRepository chatRepositoryMock;
    @Mock
    private AccessTokenValidator accessTokenValidator;
    @InjectMocks
    private UpdateChatUCImpl updateChatUC;

    @Test
    void joinChat_shouldUpdateWhenEnteringChat() {
        UpdateChatRequest request = UpdateChatRequest.builder()
                .id(1L)
                .username("fabienne")
                .build();

        ChatEntity chatEntity = ChatEntity.builder()
                .id(1L)
                .starter("peter")
                .joiner(null)
                .title("new chat")
                .build();

        ChatEntity updatedChat = ChatEntity.builder()
                .id(1L)
                .starter("peter")
                .joiner("fabienne")
                .title("new chat")
                .build();

        when(chatRepositoryMock.existsById(request.getId())).thenReturn(true);
        when(chatRepositoryMock.findChatEntityById(request.getId())).thenReturn(chatEntity);
        when(chatRepositoryMock.save(updatedChat)).thenReturn(updatedChat);

        UpdateChatResponse actualResponse = updateChatUC.joinChat(request);
        UpdateChatResponse expectedResponse = UpdateChatResponse.builder().response("Someone entered chat").build();

        assertEquals(expectedResponse, actualResponse);
        verify(chatRepositoryMock).save(updatedChat);
    }

    @Test
    void deleteChat_shouldDeleteWhenStarterIsLeavingChat() {
        UpdateChatRequest request = UpdateChatRequest.builder()
                .id(1L)
                .username("fabienne")
                .build();

        ChatEntity chatEntity = ChatEntity.builder()
                .id(1L)
                .starter("fabienne")
                .joiner(null)
                .title("new chat")
                .build();

        when(chatRepositoryMock.existsById(request.getId())).thenReturn(true);
        when(chatRepositoryMock.findChatEntityById(request.getId())).thenReturn(chatEntity);
        doNothing().when(chatRepositoryMock).deleteById(request.getId());

        UpdateChatResponse actualResponse = updateChatUC.leaveChat(request);
        UpdateChatResponse expectedResponse = UpdateChatResponse.builder().response("Chat finished and deleted").build();

        assertEquals(expectedResponse, actualResponse);
        verify(chatRepositoryMock).deleteById(request.getId());
    }

    @Test
    void leaveChat_shouldUpdateWhenLeavingChat() {
        UpdateChatRequest request = UpdateChatRequest.builder()
                .id(1L)
                .username("fabienne")
                .build();

        ChatEntity chatEntity = ChatEntity.builder()
                .id(1L)
                .starter("peter")
                .joiner("fabienne")
                .title("new chat")
                .build();

        ChatEntity updatedChat = ChatEntity.builder()
                .id(1L)
                .starter("peter")
                .joiner(null)
                .title("new chat")
                .build();

        when(chatRepositoryMock.existsById(request.getId())).thenReturn(true);
        when(chatRepositoryMock.findChatEntityById(request.getId())).thenReturn(chatEntity);
        when(chatRepositoryMock.save(updatedChat)).thenReturn(updatedChat);

        UpdateChatResponse actualResponse = updateChatUC.leaveChat(request);
        UpdateChatResponse expectedResponse = UpdateChatResponse.builder().response("Joiner left chat").build();

        assertEquals(expectedResponse, actualResponse);
        verify(chatRepositoryMock).save(updatedChat);
    }

    @Test
    void joinChat_shouldNotUpdateChatWhenChatFull() {
        UpdateChatRequest request = UpdateChatRequest.builder()
                .id(1L)
                .username("jack")
                .build();

        ChatEntity chatEntity = ChatEntity.builder()
                .id(1L)
                .starter("peter")
                .joiner("fabienne")
                .title("new chat")
                .build();

        when(chatRepositoryMock.existsById(request.getId())).thenReturn(true);
        when(chatRepositoryMock.findChatEntityById(request.getId())).thenReturn(chatEntity);

        UpdateChatResponse actualResponse = updateChatUC.joinChat(request);
        UpdateChatResponse expectedResponse = UpdateChatResponse.builder().response("Not allowed to enter chat").build();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void joinChat_shouldNotUpdateChatWhenChatDoesntExists() {
        UpdateChatRequest request = UpdateChatRequest.builder()
                .id(2L)
                .username("jack")
                .build();

        when(chatRepositoryMock.existsById(2L)).thenReturn(false);

        assertThrows(InvalidIdException.class, () -> {
            updateChatUC.joinChat(request);
        });
    }

    @Test
    void leaveChat_shouldNotUpdateChatWhenChatDoesntExists() {
        UpdateChatRequest request = UpdateChatRequest.builder()
                .id(2L)
                .username("jack")
                .build();

        when(chatRepositoryMock.existsById(2L)).thenReturn(false);

        assertThrows(InvalidIdException.class, () -> {
            updateChatUC.leaveChat(request);
        });
    }
}