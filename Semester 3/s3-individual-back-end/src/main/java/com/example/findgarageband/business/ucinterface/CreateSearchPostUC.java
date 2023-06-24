package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.Searchpost.CreateSearchPostRequest;
import com.example.findgarageband.domain.Searchpost.CreateSearchPostResponse;

public interface CreateSearchPostUC {
    CreateSearchPostResponse createSearchPost(CreateSearchPostRequest request);
}
