package com.example.findgarageband.business.impl.chatimpl;

import com.example.findgarageband.domain.chat.Chat;
import com.example.findgarageband.domain.chat.GetAllChatsResponse;
import com.example.findgarageband.persistence.ChatRepository;
import com.example.findgarageband.persistence.entity.ChatEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllChatsUCImplTest {
    @Mock
    private ChatRepository chatRepositoryMock;
    @InjectMocks
    private GetAllChatsUCImpl getAllChatsUC;

    @Test
    void getAllChats_shouldReturnAllChatsConverted() {
        List<ChatEntity> chats = new ArrayList<>();
        chats.add(ChatEntity.builder()
                .id(1L)
                .title("QnA session")
                .starter("fabienne")
                .joiner(null)
                .build());

        chats.add(ChatEntity.builder()
                .id(2L)
                .title("other QnA session")
                .starter("jack")
                .joiner(null)
                .build());

        when(chatRepositoryMock.findChatEntitiesByJoinerNull()).thenReturn(chats);
        GetAllChatsResponse actualResult = getAllChatsUC.getAllChats();

        Chat chat1 = Chat.builder()
                .id(1L)
                .title("QnA session")
                .starter("fabienne")
                .joiner(null)
                .build();

        Chat chat2 = Chat.builder()
                .id(2L)
                .title("other QnA session")
                .starter("jack")
                .joiner(null)
                .build();

        GetAllChatsResponse expectedResult = GetAllChatsResponse.builder()
                .chats(List.of(chat1, chat2))
                .build();

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult.getChats().size(), actualResult.getChats().size());
        verify(chatRepositoryMock).findChatEntitiesByJoinerNull();
    }
}