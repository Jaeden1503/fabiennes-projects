package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.CreateCommentUC;
import com.example.findgarageband.business.ucinterface.DeleteCommentUC;
import com.example.findgarageband.business.ucinterface.GetAllCommentsUC;
import com.example.findgarageband.business.ucinterface.GetCommentsByUserUC;
import com.example.findgarageband.configuration.security.isauthenticated.IsAuthenticated;
import com.example.findgarageband.domain.Comment.CreateCommentRequest;
import com.example.findgarageband.domain.Comment.CreateCommentResponse;
import com.example.findgarageband.domain.Comment.GetAllCommentsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private final GetAllCommentsUC getAllCommentsUC;
    private final GetCommentsByUserUC getCommentsByUserUC;
    private final CreateCommentUC createCommentUC;
    private final DeleteCommentUC deleteCommentUC;

    @GetMapping()
    @IsAuthenticated
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<GetAllCommentsResponse> getAllComments() {
        return ResponseEntity.ok(getAllCommentsUC.getAllComments());
    }

    @GetMapping("user/{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<GetAllCommentsResponse> getAllCommentsByUser(@PathVariable(value = "id") final long id) {
        return ResponseEntity.ok(getCommentsByUserUC.getCommentsByUserId(id));
    }

    @PostMapping
    @IsAuthenticated
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody @Valid CreateCommentRequest request) {
        CreateCommentResponse response = createCommentUC.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> deleteComment(@PathVariable(value = "id") final long id) {
        deleteCommentUC.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
