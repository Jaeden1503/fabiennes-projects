package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.CreateUserUC;
import com.example.findgarageband.business.ucinterface.GetAllUsersUC;
import com.example.findgarageband.business.ucinterface.GetUserUC;
import com.example.findgarageband.business.ucinterface.UpdateUserUC;
import com.example.findgarageband.domain.User.*;
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
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetAllUsersUC getAllUsersUCMock;
    @MockBean
    private GetUserUC getUserUCMock;
    @MockBean
    private UpdateUserUC updateUserUCMock;
    @MockBean
    private CreateUserUC createUserUCMock;

    //////////// get all users ////////////
    @WithMockUser(roles = {"ADMIN"})
    @Test
    void getUsers_shouldReturn200ResponseWithUsersArray() throws Exception {
        GetAllUsersResponse response = GetAllUsersResponse.builder()
                .users(List.of(
                        User.builder().id(1L).username("henk").email("henk@gmail.com").about("this is my new profile description!").build(),
                        User.builder().id(2L).username("jan").email("jan@gmail.com").about("this is my new profile description!").build()
                ))
                .build();
        when(getAllUsersUCMock.getAllUsers()).thenReturn(response);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"users":[{"id":1,"username":"henk","email":"henk@gmail.com", "about": "this is my new profile description!"},
                            {"id":2,"username":"jan","email":"jan@gmail.com", "about": "this is my new profile description!"}
                            ]}
                        """));

        verify(getAllUsersUCMock).getAllUsers();
        verifyNoInteractions(createUserUCMock);
    }

    //////////// get user ////////////
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void getUser_shouldReturn200ResponseWithUser() throws Exception {
        User response = User.builder()
                .id(1L)
                .username("henk")
                .email("henk@gmail.com")
                .about("this is my new profile description!")
                .build();
        when(getUserUCMock.getUser(1L)).thenReturn(response);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"id":1,"username":"henk","email":"henk@gmail.com", "about": "this is my new profile description!"}
                        """));

        verify(getUserUCMock).getUser(1L);
        verifyNoInteractions(createUserUCMock);
    }

    //////////// create user ////////////
    @Test
    void createUser_shouldCreateAndReturn201_WhenRequestValid() throws Exception {
        CreateUserRequest expectedUser = CreateUserRequest.builder()
                .username("henk")
                .email("henk@gmail.com")
                .password("secure_password")
                .build();
        when(createUserUCMock.createUser(expectedUser)).thenReturn(CreateUserResponse.builder().userId(100L).build());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                               {                              
                                    "username": "henk",
                                    "email": "henk@gmail.com",
                                    "password": "secure_password"
                               }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       {"userId": 100}
                    """));

        verify(createUserUCMock).createUser(expectedUser);
    }

    @Test
    void createUser_shouldNotCreateAndReturn400_WhenMissingFields() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                               {                              
                                    "username": "",
                                    "email": "",
                                    "password": ""
                               }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       [
                        {"field":"username","error":"must not be blank"},
                        {"field":"email","error":"must not be blank"},
                        {"field":"password","error":"must not be blank"}
                       ]
                    """));

        verifyNoInteractions(createUserUCMock);
    }

    //////////// update user ////////////
    @WithMockUser(roles = "USER")
    @Test
    void updateUser_shouldUpdateAndReturn204_WhenRequestValid() throws Exception {
        UpdateUserRequest updatedUser = UpdateUserRequest.builder()
                .id(1L)
                .username("jan")
                .email("henk@gmail.com")
                .password("secure_password")
                .about("this is my new profile description!")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/{id}", 1)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                               {                              
                                    "username": "jan",
                                    "email": "henk@gmail.com",
                                    "password": "secure_password",
                                    "about": "this is my new profile description!"
                               }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(updateUserUCMock).updateUser(updatedUser);
    }
}