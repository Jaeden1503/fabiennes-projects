package com.example.findgarageband.business.impl;

import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.persistence.entity.SearchPostEntity;

public final class SearchPostConverter {
    private SearchPostConverter() {

    }

    //converts from searchpost to searchpost entity
    public static SearchPost convert(SearchPostEntity searchPost) {
        return SearchPost.builder()
                .id(searchPost.getId())
                .title(searchPost.getTitle())
                .description(searchPost.getDescription())
                .searchingBand(searchPost.getSearchingBand())
                .date(searchPost.getDate())
                .instrument(searchPost.getInstrument())
                .province(searchPost.getProvince())
                .user(UserConverter.convert(searchPost.getUserEntity()))
                .build();
    }
}
