package com.example.findgarageband.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "chat")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 6, max = 36)
    @Column(name = "starter")
    private String starter;

    @Length(min = 6, max = 254)
    @Column(name = "joiner")
    private String joiner;

    @Length(max = 100)
    @Column(name = "title")
    private String title;
}
