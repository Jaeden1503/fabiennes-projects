package com.example.findgarageband.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BandMusicianCount {
    private Long searchingForBand;
    private Long searchingForMusician;
}
