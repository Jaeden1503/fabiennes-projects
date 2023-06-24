package com.example.siouxmanagementsystem.business.UseCases.SecretaryUseCases;

import com.example.siouxmanagementsystem.domain.Secretary.CreateSecretaryRequest;
import com.example.siouxmanagementsystem.domain.Secretary.CreateSecretaryResponse;

public interface CreateSecretaryUseCase {
    CreateSecretaryResponse createSecretary(CreateSecretaryRequest request);
}
