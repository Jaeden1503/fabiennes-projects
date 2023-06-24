package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.BandMusicianCount;
import com.example.findgarageband.domain.InstrumentOccurrence;
import com.example.findgarageband.domain.TopPostsResponse;

public interface GetStatisticsUC {
    TopPostsResponse getMostCommentedPosts();
    BandMusicianCount getBandVSMusicianCount();
    InstrumentOccurrence getInstrumentOccurrenceCount();
}
