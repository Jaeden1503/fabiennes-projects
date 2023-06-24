package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.CreateChatUC;
import com.example.findgarageband.business.ucinterface.GetAllChatsUC;
import com.example.findgarageband.business.ucinterface.GetChatUC;
import com.example.findgarageband.business.ucinterface.UpdateChatUC;
import com.example.findgarageband.configuration.security.isauthenticated.IsAuthenticated;
import com.example.findgarageband.domain.chat.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {
    private final CreateChatUC createChatUC;
    private final GetAllChatsUC getAllChatsUC;
    private final UpdateChatUC updateChatUC;
    private final GetChatUC getChatUC;

    @PostMapping
    @IsAuthenticated
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<CreateChatResponse> createChat (@RequestBody @Valid CreateChatRequest request) {
        CreateChatResponse response = createChatUC.createChat(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    @IsAuthenticated
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<Chat> getChat(@PathVariable(value = "id") final long id) {
        return ResponseEntity.ok().body(getChatUC.getChat(id));
    }

    @GetMapping
    public ResponseEntity<GetAllChatsResponse> getAllChats() {
        return ResponseEntity.ok(getAllChatsUC.getAllChats());
    }

    @PutMapping("/leave/{id}")
    @IsAuthenticated
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<UpdateChatResponse> leaveChat(@PathVariable("id") long id,
                                                         @RequestBody @Valid UpdateChatRequest request) {
        request.setId(id);
        UpdateChatResponse response = updateChatUC.leaveChat(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/join/{id}")
    @IsAuthenticated
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<UpdateChatResponse> joinChat(@PathVariable("id") long id,
                                                        @RequestBody @Valid UpdateChatRequest request) {
        request.setId(id);
        UpdateChatResponse response = updateChatUC.joinChat(request);
        return ResponseEntity.ok(response);
    }
}
