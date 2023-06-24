package com.example.findgarageband.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "searchpost")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(max = 70)
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "searchingband")
    private Boolean searchingBand;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @NotBlank
    @Length(min = 3, max = 100)
    @Column(name = "instrument")
    private String instrument;

    @NotBlank
    @Length(min = 2, max = 64)
    @Column(name = "province")
    private String province;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
