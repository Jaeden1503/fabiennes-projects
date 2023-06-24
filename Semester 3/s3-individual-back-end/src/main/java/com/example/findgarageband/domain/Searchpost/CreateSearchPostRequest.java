package com.example.findgarageband.domain.Searchpost;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSearchPostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Boolean searchingBand;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String instrument;

    @NotBlank
    private String province;

    @NotNull
    private Long userId;
}
