package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.LoginUC;
import com.example.findgarageband.domain.LoginRequest;
import com.example.findgarageband.domain.LoginResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LoginUC loginUCMock;

    @Test
    void login_shouldReturn200AndAccessToken_WhenCredentialsCorrect() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("henk@gmail.com")
                .password("secure_password")
                .build();
        when(loginUCMock.login(request)).thenReturn(LoginResponse.builder().accessToken("newaccessToken").build());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""                            
                       {
                            "email": "henk@gmail.com",
                            "password": "secure_password"
                       }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       {"accessToken": "newaccessToken"}
                    """));

        verify(loginUCMock).login(request);
    }
}