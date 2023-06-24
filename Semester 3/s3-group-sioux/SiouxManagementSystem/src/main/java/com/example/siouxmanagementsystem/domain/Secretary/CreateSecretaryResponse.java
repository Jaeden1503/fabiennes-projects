package com.example.siouxmanagementsystem.domain.Secretary;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSecretaryResponse {
    private Long secretaryId;
}
