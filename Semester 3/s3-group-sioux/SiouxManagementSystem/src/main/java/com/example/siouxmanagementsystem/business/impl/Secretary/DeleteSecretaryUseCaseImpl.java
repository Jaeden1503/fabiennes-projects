package com.example.siouxmanagementsystem.business.impl.Secretary;

import com.example.siouxmanagementsystem.business.UseCases.SecretaryUseCases.DeleteSecretaryUseCase;
import com.example.siouxmanagementsystem.persistence.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteSecretaryUseCaseImpl implements DeleteSecretaryUseCase {
    private final SecretaryRepository secretaryRepository;

    @Override
    @Transactional
    public void deleteSecretary(long secretaryId) {
        this.secretaryRepository.deleteById(secretaryId);
    }
}
