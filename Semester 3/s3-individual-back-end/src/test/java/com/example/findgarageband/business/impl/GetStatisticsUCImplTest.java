package com.example.findgarageband.business.impl;

import com.example.findgarageband.domain.BandMusicianCount;
import com.example.findgarageband.domain.InstrumentOccurrence;
import com.example.findgarageband.domain.Searchpost.MostCommentedPost;
import com.example.findgarageband.domain.TopPostsResponse;
import com.example.findgarageband.persistence.SearchPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStatisticsUCImplTest {
    @Mock
    private SearchPostRepository searchPostRepository;
    @InjectMocks
    private GetStatisticsUCImpl getStatisticsUC;

    @Test
    void getTop10_shouldReturnMostPopularSearchPostsAndCount() {
        List<MostCommentedPost> popularPostCount = new ArrayList<>();
        popularPostCount.add(MostCommentedPost.builder()
                .searchPostId(1L)
                .searchPostTitle("title")
                .amountComments(4L)
                .build());

        when(searchPostRepository.findSearchPostsWithMostComments(any(Pageable.class)))
                .thenReturn(new PageImpl<MostCommentedPost>(popularPostCount));

        TopPostsResponse actualResult = getStatisticsUC.getMostCommentedPosts();

        MostCommentedPost post = MostCommentedPost.builder()
                .searchPostId(1L)
                .searchPostTitle("title")
                .amountComments(4L)
                .build();

        TopPostsResponse expectedResult = TopPostsResponse.builder()
                .popularPosts(List.of(post))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(searchPostRepository).findSearchPostsWithMostComments(Pageable.ofSize(10));
    }

    @Test
    void getCountBandVSMusician_shouldReturnMostCounts() {
        when(searchPostRepository.countBySearchingBandTrue()).thenReturn(4L);
        when(searchPostRepository.countBySearchingBandFalse()).thenReturn(8L);

        BandMusicianCount actualResult = getStatisticsUC.getBandVSMusicianCount();

        BandMusicianCount expectedResult = BandMusicianCount.builder()
                .searchingForBand(4L)
                .searchingForMusician(8L)
                .build();

        assertEquals(expectedResult, actualResult);
        verify(searchPostRepository).countBySearchingBandTrue();
        verify(searchPostRepository).countBySearchingBandFalse();
    }

    @Test
    void getInstrumentOccurrence_shouldReturnMostCountOfEachInstrument() {
        when(searchPostRepository.countByInstrument("guitar")).thenReturn(2L);
        when(searchPostRepository.countByInstrument("piano")).thenReturn(4L);
        when(searchPostRepository.countByInstrument("drums")).thenReturn(0L);
        when(searchPostRepository.countByInstrument("bass")).thenReturn(6L);
        when(searchPostRepository.countByInstrument("trumpet")).thenReturn(3L);
        when(searchPostRepository.countByInstrument("flute")).thenReturn(2L);
        when(searchPostRepository.countByInstrument("vocalist")).thenReturn(5L);
        when(searchPostRepository.countByInstrument("violin")).thenReturn(3L);

        InstrumentOccurrence actualResult = getStatisticsUC.getInstrumentOccurrenceCount();

        InstrumentOccurrence expectedResult = InstrumentOccurrence.builder()
                .guitar(2L)
                .piano(4L)
                .drums(0L)
                .bass(6L)
                .trumpet(3L)
                .flute(2L)
                .singer(5L)
                .violin(3L)
                .build();

        assertEquals(expectedResult, actualResult);
        verify(searchPostRepository).countByInstrument("guitar");
    }
}