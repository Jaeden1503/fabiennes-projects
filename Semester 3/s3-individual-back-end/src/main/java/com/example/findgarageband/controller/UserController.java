package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.*;
import com.example.findgarageband.configuration.security.isauthenticated.IsAuthenticated;

import com.example.findgarageband.domain.User.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final CreateUserUC createUserUC;
    private final GetAllUsersUC getAllUsersUC;
    private final UpdateUserUC updateUserUC;
    private final GetUserUC getUserUC;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser (@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUC.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @IsAuthenticated
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        return ResponseEntity.ok(getAllUsersUC.getAllUsers());
    }

    @PutMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateUserRequest request) {
        request.setId(id);
        updateUserUC.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id) {
        return ResponseEntity.ok().body(getUserUC.getUser(id));
    }
}