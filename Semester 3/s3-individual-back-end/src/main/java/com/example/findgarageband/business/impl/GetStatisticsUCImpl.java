package com.example.findgarageband.business.impl;

import com.example.findgarageband.business.ucinterface.GetStatisticsUC;
import com.example.findgarageband.domain.BandMusicianCount;
import com.example.findgarageband.domain.InstrumentOccurrence;
import com.example.findgarageband.domain.Searchpost.MostCommentedPost;
import com.example.findgarageband.domain.TopPostsResponse;
import com.example.findgarageband.persistence.SearchPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetStatisticsUCImpl implements GetStatisticsUC {
    private final SearchPostRepository searchPostRepository;

    @Transactional
    @Override
    public TopPostsResponse getMostCommentedPosts() {
        List<MostCommentedPost> popularPosts = searchPostRepository.findSearchPostsWithMostComments(Pageable.ofSize(10))
                .getContent();

        return TopPostsResponse.builder()
                .popularPosts(popularPosts)
                .build();
    }

    @Transactional
    @Override
    public BandMusicianCount getBandVSMusicianCount() {
        Long bNumber = searchPostRepository.countBySearchingBandTrue(); //number of posts looking for a band
        Long mNumber = searchPostRepository.countBySearchingBandFalse(); //number of posts looking for a musician

        return BandMusicianCount.builder()
                .searchingForBand(bNumber)
                .searchingForMusician(mNumber)
                .build();
    }

    @Transactional
    @Override
    public InstrumentOccurrence getInstrumentOccurrenceCount() {
        Long guitar = searchPostRepository.countByInstrument("guitar");
        Long piano = searchPostRepository.countByInstrument("piano");
        Long drums = searchPostRepository.countByInstrument("drums");
        Long bass = searchPostRepository.countByInstrument("bass");
        Long trumpet = searchPostRepository.countByInstrument("trumpet");
        Long flute = searchPostRepository.countByInstrument("flute");
        Long singer = searchPostRepository.countByInstrument("vocalist");
        Long violin = searchPostRepository.countByInstrument("violin");

        return InstrumentOccurrence.builder()
                .guitar(guitar)
                .piano(piano)
                .drums(drums)
                .bass(bass)
                .trumpet(trumpet)
                .flute(flute)
                .singer(singer)
                .violin(violin)
                .build();
    }
}
