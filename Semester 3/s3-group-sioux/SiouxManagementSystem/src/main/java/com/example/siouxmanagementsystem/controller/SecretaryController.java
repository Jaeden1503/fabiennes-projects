package com.example.siouxmanagementsystem.controller;

import com.example.siouxmanagementsystem.business.UseCases.SecretaryUseCases.CreateSecretaryUseCase;
import com.example.siouxmanagementsystem.business.UseCases.SecretaryUseCases.DeleteSecretaryUseCase;
import com.example.siouxmanagementsystem.business.UseCases.SecretaryUseCases.UpdateSecretaryUseCase;
import com.example.siouxmanagementsystem.configuration.security.isauthenticated.IsAuthenticated;
import com.example.siouxmanagementsystem.domain.Secretary.CreateSecretaryRequest;
import com.example.siouxmanagementsystem.domain.Secretary.CreateSecretaryResponse;
import com.example.siouxmanagementsystem.domain.Secretary.Secretary;
import com.example.siouxmanagementsystem.domain.Secretary.UpdateSecretaryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/secretaries")
@RequiredArgsConstructor
public class SecretaryController {

    private final DeleteSecretaryUseCase deleteSecretaryUseCase;
    private final CreateSecretaryUseCase createSecretaryUseCase;
    private final UpdateSecretaryUseCase updateSecretaryUseCase;

    @DeleteMapping("{secretaryId}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteSecretary(@PathVariable int secretaryId) {
        deleteSecretaryUseCase.deleteSecretary(secretaryId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateSecretaryResponse> createSecretary(@RequestBody @Valid CreateSecretaryRequest request) {
        CreateSecretaryResponse response = createSecretaryUseCase.createSecretary(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    @IsAuthenticated
    public ResponseEntity<Secretary> updateSecretary(@PathVariable("id") long id, @RequestBody @Valid UpdateSecretaryRequest request) {
        request.setId(id);
        updateSecretaryUseCase.updateSecretary(request);
        return ResponseEntity.noContent().build();
    }

}
