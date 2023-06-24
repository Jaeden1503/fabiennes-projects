package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.*;
import com.example.findgarageband.configuration.security.isauthenticated.IsAuthenticated;
import com.example.findgarageband.domain.Searchpost.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/searchposts")
@AllArgsConstructor
public class SearchPostController {

    private final GetSearchPostUC getSearchPostUC;
    private final GetAllSearchPostsUC getAllSearchPostsUC;
    private final CreateSearchPostUC createSearchPostUC;
    private final FilterSearchPostsUC filterSearchPostsUC;
    private final GetSearchPostByUserUC getSearchPostByUserUC;
    private final DeleteSearchPostUC deleteSearchPostUC;

    @GetMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<GetSearchPostAndCommentsResponse> getSearchPostAndComments(@PathVariable(value = "id") final long id) {
        return ResponseEntity.ok().body(getSearchPostUC.getSearchPostAndComments(id));
    }

    @GetMapping("searchpost/{id}")
    public ResponseEntity<SearchPost> getSearchPost(@PathVariable(value = "id") final long id) {
        return ResponseEntity.ok().body(getSearchPostUC.getSearchPost(id));
    }

    @GetMapping
    public ResponseEntity<GetAllSearchPostsResponse> getAllSearchPosts() {
        return ResponseEntity.ok(getAllSearchPostsUC.getAllSearchPosts());
    }

    @GetMapping("paginated/{id}")
    @IsAuthenticated
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<GetPostsPaginatedResponse> getAllSearchPostsPaginated(@PathVariable(value = "id") final long id) {
        return ResponseEntity.ok(getAllSearchPostsUC.getAllSearchPostsPaginated((int) id));
    }

    @GetMapping("user/{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<GetAllSearchPostsResponse> getSearchPostsByUser(@PathVariable(value = "id") final long id) {
        return ResponseEntity.ok(getSearchPostByUserUC.getAllSearchPostsByUserId(id));
    }

    @PostMapping
    @IsAuthenticated
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<CreateSearchPostResponse> createSearchPost (@RequestBody @Valid CreateSearchPostRequest request) {
        CreateSearchPostResponse response = createSearchPostUC.createSearchPost(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<FilterSearchPostResponse> getAllFilteredSearchPosts(@RequestParam(value = "searchingBand", required = false) Boolean searchingBand,
                                                                              @RequestParam(value = "instrument", required = false) String instrument,
                                                                              @RequestParam(value = "province", required = false) String province) {
        
        FilterSearchPostRequest request = FilterSearchPostRequest.builder()
                .searchingBand(searchingBand)
                .instrument(instrument)
                .province(province)
                .build();

        return ResponseEntity.ok(filterSearchPostsUC.filterSearchPosts(request));
    }

    @DeleteMapping("{id}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> deleteSearchPost(@PathVariable(value = "id") final long id) {
        deleteSearchPostUC.deleteSearchPost(id);
        return ResponseEntity.noContent().build();
    }
}
