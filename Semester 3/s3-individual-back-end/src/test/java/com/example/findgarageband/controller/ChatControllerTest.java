package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.CreateChatUC;
import com.example.findgarageband.business.ucinterface.GetAllChatsUC;
import com.example.findgarageband.business.ucinterface.GetChatUC;
import com.example.findgarageband.business.ucinterface.UpdateChatUC;
import com.example.findgarageband.domain.chat.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateChatUC createChatUCMock;
    @MockBean
    private GetAllChatsUC getAllChatsUCMock;
    @MockBean
    private UpdateChatUC updateChatUCMock;
    @MockBean
    private GetChatUC getChatUC;

    @Test
    void getChats_shouldReturn200ResponseWithChatArray() throws Exception {
        Chat chat1 = Chat.builder()
                .id(1L)
                .starter("peter")
                .joiner(null)
                .title("new chat")
                .build();

        Chat chat2 = Chat.builder()
                .id(2L)
                .starter("jack")
                .joiner(null)
                .title("other chat")
                .build();

        GetAllChatsResponse response = GetAllChatsResponse.builder()
                .chats(List.of(chat1, chat2))
                .build();

        when(getAllChatsUCMock.getAllChats()).thenReturn(response);

        mockMvc.perform(get("/chat"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"chats":[{"id":1,"starter":"peter","joiner":null, "title": "new chat"},
                            {"id":2,"starter":"jack","joiner":null, "title": "other chat"}
                            ]}
                        """));

        verify(getAllChatsUCMock).getAllChats();
        verifyNoInteractions(createChatUCMock);
    }

    @WithMockUser(roles = "USER")
    @Test
    void createChat_shouldCreateAndReturn201_WhenRequestValid() throws Exception {
        CreateChatRequest request = CreateChatRequest.builder()
                .starter("fabienne")
                .title("new chat")
                .build();
        when(createChatUCMock.createChat(request)).thenReturn(CreateChatResponse.builder().chatId(100L).build());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/chat")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                               {                              
                                    "starter": "fabienne",
                                    "title": "new chat"
                               }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       {"chatId": 100}
                    """));

        verify(createChatUCMock).createChat(request);
    }

    @WithMockUser(roles = "USER")
    @Test
    void createChat_shouldNotCreateAndReturn400_WhenMissingFields() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/chat")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                               {                              
                                    "starter": "",
                                    "title": ""
                               }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       [
                        {"field":"starter","error":"must not be blank"},
                        {"field":"title","error":"must not be blank"}
                       ]
                    """));

        verifyNoInteractions(createChatUCMock);
    }

    @WithMockUser(roles = "USER")
    @Test
    void joinChat_shouldUpdateAndReturn204_WhenRequestValid() throws Exception {
        UpdateChatRequest request = UpdateChatRequest.builder()
                .id(1L)
                .username("fabienne")
                .userId(1L)
                .build();

        when(updateChatUCMock.joinChat(request)).thenReturn(UpdateChatResponse.builder().response("Someone entered chat").build());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/chat/join/{id}", 1)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                               {                              
                                    "username": "fabienne",
                                    "userId": "1"
                               }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       {"response": "Someone entered chat"}
                    """));

        verify(updateChatUCMock).joinChat(request);
    }

    @WithMockUser(roles = "USER")
    @Test
    void leaveChat_shouldUpdateAndReturn204_WhenRequestValid() throws Exception {
        UpdateChatRequest request = UpdateChatRequest.builder()
                .id(1L)
                .username("fabienne")
                .userId(1L)
                .build();

        when(updateChatUCMock.leaveChat(request)).thenReturn(UpdateChatResponse.builder().response("Joiner left chat").build());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/chat/leave/{id}", 1)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                               {                              
                                    "username": "fabienne",
                                    "userId": "1"
                               }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       {"response": "Joiner left chat"}
                    """));

        verify(updateChatUCMock).leaveChat(request);
    }

    @WithMockUser(roles = "USER")
    @Test
    void getChat_shouldReturn200ResponseWithChat() throws Exception {
        Chat response = Chat.builder()
                .id(1L)
                .title("other QnA session")
                .starter("jack")
                .joiner("sam")
                .build();

        when(getChatUC.getChat(1L)).thenReturn(response);

        mockMvc.perform(get("/chat/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            { 
                                "id": 1,
                                "title": "other QnA session",
                                "starter": "jack",
                                "joiner": "sam"    
                            }
                        """));

        verify(getChatUC).getChat(1L);
    }
}