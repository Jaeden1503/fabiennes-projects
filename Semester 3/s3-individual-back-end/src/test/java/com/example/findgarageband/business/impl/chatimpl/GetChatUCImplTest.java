package com.example.findgarageband.business.impl.chatimpl;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.domain.chat.Chat;
import com.example.findgarageband.persistence.ChatRepository;
import com.example.findgarageband.persistence.entity.ChatEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetChatUCImplTest {
    @Mock
    private ChatRepository chatRepositoryMock;
    @InjectMocks
    private GetChatUCImpl getChatUC;

    @Test
    void getChat_shouldReturnChatByIdConverted() {
        ChatEntity chatEntity = ChatEntity.builder()
                .id(1L)
                .title("other QnA session")
                .starter("jack")
                .joiner("sam")
                .build();

        when(chatRepositoryMock.existsById(chatEntity.getId())).thenReturn(true);
        when(chatRepositoryMock.findChatEntityById(chatEntity.getId())).thenReturn(chatEntity);

        Chat actualResponse = getChatUC.getChat(1L);

        Chat expectedResponse = Chat.builder()
                .id(1L)
                .title("other QnA session")
                .starter("jack")
                .joiner("sam")
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(chatRepositoryMock).existsById(1L);
        verify(chatRepositoryMock).findChatEntityById(1L);
    }

    @Test
    void getChat_shouldReturnInvalidIdException() {
        when(chatRepositoryMock.existsById(2L)).thenReturn(false);

        assertThrows(InvalidIdException.class, () -> {
            getChatUC.getChat(2L);
        });
    }
}