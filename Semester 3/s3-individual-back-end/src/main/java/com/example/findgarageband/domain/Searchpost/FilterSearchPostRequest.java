package com.example.findgarageband.domain.Searchpost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterSearchPostRequest {
    private Boolean searchingBand;
    private String instrument;
    private String province;
}
