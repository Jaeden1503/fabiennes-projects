package com.example.siouxmanagementsystem.business.impl.Secretary;

import com.example.siouxmanagementsystem.business.UseCases.SecretaryUseCases.UpdateSecretaryUseCase;
import com.example.siouxmanagementsystem.business.exception.InvalidSecretaryException;
import com.example.siouxmanagementsystem.domain.Secretary.UpdateSecretaryRequest;
import com.example.siouxmanagementsystem.persistence.SecretaryRepository;
import com.example.siouxmanagementsystem.persistence.entity.SecretaryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateSecretaryUseCaseImpl implements UpdateSecretaryUseCase {
    private final SecretaryRepository secretaryRepository;

    @Transactional
    @Override
    public void updateSecretary(UpdateSecretaryRequest request) {
        Optional<SecretaryEntity> secretaryOptional = secretaryRepository.findById(request.getId());
        if (secretaryOptional.isEmpty()) {
            throw new InvalidSecretaryException("SECRETARY_ID_INVALID");
        }

        SecretaryEntity secretary = secretaryOptional.get();
        updateFields(request, secretary);
    }

    private void updateFields(UpdateSecretaryRequest request, SecretaryEntity secretary) {
        secretary.setName(request.getName());

        secretaryRepository.save(secretary);
    }
}
