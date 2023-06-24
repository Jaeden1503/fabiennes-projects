package com.example.siouxmanagementsystem.domain.Secretary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSecretaryRequest {
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    private String email_address;
}
