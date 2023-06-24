package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.GetStatisticsUC;
import com.example.findgarageband.configuration.security.isauthenticated.IsAuthenticated;
import com.example.findgarageband.domain.BandMusicianCount;
import com.example.findgarageband.domain.InstrumentOccurrence;
import com.example.findgarageband.domain.TopPostsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {
    private final GetStatisticsUC getStatisticsUC;

    @GetMapping("/topposts")
    @IsAuthenticated
    @RolesAllowed("ADMIN")
    public ResponseEntity<TopPostsResponse> top10Posts() {
        return ResponseEntity.ok(getStatisticsUC.getMostCommentedPosts());
    }

    @GetMapping("/bandormusician")
    @IsAuthenticated
    @RolesAllowed("ADMIN")
    public ResponseEntity<BandMusicianCount> bandOrMusician() {
        return ResponseEntity.ok(getStatisticsUC.getBandVSMusicianCount());
    }

    @GetMapping("/instrument")
    @IsAuthenticated
    @RolesAllowed("ADMIN")
    public ResponseEntity<InstrumentOccurrence> instrumentOccurrence() {
        return ResponseEntity.ok(getStatisticsUC.getInstrumentOccurrenceCount());
    }
}
