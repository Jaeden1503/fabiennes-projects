package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.FilterSearchPostsUC;
import com.example.findgarageband.business.impl.SearchPostConverter;
import com.example.findgarageband.domain.Searchpost.FilterSearchPostRequest;
import com.example.findgarageband.domain.Searchpost.FilterSearchPostResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class FilterSearchPostsUCImpl implements FilterSearchPostsUC {
    private final SearchPostRepository searchPostRepository;

    @Transactional
    @Override
    public FilterSearchPostResponse filterSearchPosts(FilterSearchPostRequest request) {
        SearchPostEntity searchPostEntity = SearchPostEntity.builder()
                .searchingBand(request.getSearchingBand())
                .instrument(request.getInstrument().equals("") ? null : request.getInstrument())
                .province(request.getProvince().equals("") ? null : request.getProvince())
                .build();

        //nulls are being ignored when using Example.of
        List<SearchPost> searchPosts = searchPostRepository.findAll(Example.of(searchPostEntity))
                .stream()
                .map(SearchPostConverter::convert)
                .toList();

        return FilterSearchPostResponse.builder()
                .searchPosts(searchPosts)
                .build();
    }
}
