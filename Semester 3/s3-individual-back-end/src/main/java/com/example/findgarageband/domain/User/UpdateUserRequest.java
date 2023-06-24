package com.example.findgarageband.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private String password;

    private String about;
}
