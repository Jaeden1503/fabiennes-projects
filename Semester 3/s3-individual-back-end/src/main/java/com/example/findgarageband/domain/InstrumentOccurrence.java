package com.example.findgarageband.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentOccurrence {
    private Long guitar;
    private Long piano;
    private Long drums;
    private Long bass;
    private Long trumpet;
    private Long flute;
    private Long singer;
    private Long violin;
}
