package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.GetStatisticsUC;
import com.example.findgarageband.domain.BandMusicianCount;
import com.example.findgarageband.domain.InstrumentOccurrence;
import com.example.findgarageband.domain.Searchpost.MostCommentedPost;
import com.example.findgarageband.domain.TopPostsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class StatisticsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetStatisticsUC getStatisticsUCMock;

    //////////// get top 10  ////////////
    @WithMockUser(roles = "ADMIN")
    @Test
    void getTop10_shouldGetAndReturn200ResponseWithTop10() throws Exception {
        MostCommentedPost post1 = MostCommentedPost.builder()
                .searchPostId(3L)
                .searchPostTitle("this is searchpost 2")
                .amountComments(3L)
                .build();

        MostCommentedPost post2 = MostCommentedPost.builder()
                .searchPostId(7L)
                .searchPostTitle("attention please")
                .amountComments(1L)
                .build();

        TopPostsResponse response = TopPostsResponse.builder()
                .popularPosts(List.of(post1, post2))
                .build();

        when(getStatisticsUCMock.getMostCommentedPosts()).thenReturn(response);

        mockMvc.perform(get("/statistics/topposts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {       
                                "popularPosts": [
                                          {
                                              "searchPostId": 3,
                                              "searchPostTitle": "this is searchpost 2",
                                              "amountComments": 3
                                          },
                                          {
                                              "searchPostId": 7,
                                              "searchPostTitle": "attention please",
                                              "amountComments": 1
                                          }
                                      ]}
                        """));

        verify(getStatisticsUCMock).getMostCommentedPosts();
    }

    //////////// get count Searching for band VS musician  ////////////
    @WithMockUser(roles = "ADMIN")
    @Test
    void getBvsM_shouldGetAndReturn200ResponseCountBandVSMusician() throws Exception {
        BandMusicianCount response = BandMusicianCount.builder()
                .searchingForBand(4L)
                .searchingForMusician(8L)
                .build();

        when(getStatisticsUCMock.getBandVSMusicianCount()).thenReturn(response);

        mockMvc.perform(get("/statistics/bandormusician"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {       
                                "searchingForBand": 4,
                                "searchingForMusician": 8
                            }
                        """));

        verify(getStatisticsUCMock).getBandVSMusicianCount();
    }

    //////////// get occurrence count of each instrument  ////////////
    @WithMockUser(roles = "ADMIN")
    @Test
    void getInstrumentOccurrence_shouldGetAndReturn200ResponseCountOfEachInstrument() throws Exception {
        InstrumentOccurrence response = InstrumentOccurrence.builder()
                .guitar(2L)
                .piano(4L)
                .drums(0L)
                .bass(6L)
                .trumpet(3L)
                .flute(2L)
                .singer(5L)
                .violin(3L)
                .build();

        when(getStatisticsUCMock.getInstrumentOccurrenceCount()).thenReturn(response);

        mockMvc.perform(get("/statistics/instrument"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {       
                                "guitar": 2,
                                "piano": 4,
                                "drums": 0,
                                "bass": 6,
                                "trumpet": 3,
                                "flute": 2,
                                "singer": 5,
                                "violin": 3
                            }
                        """));

        verify(getStatisticsUCMock).getInstrumentOccurrenceCount();
    }
}