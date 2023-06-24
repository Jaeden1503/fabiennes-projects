package com.example.findgarageband.domain.Searchpost;

import com.example.findgarageband.domain.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPost {
    private Long id;
    private String title;
    private String description;
    private Boolean searchingBand;
    private LocalDate date;
    private String instrument;
    private String province;
    private User user;
}
