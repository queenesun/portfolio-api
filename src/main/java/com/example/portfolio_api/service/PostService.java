package com.example.portfolio_api.service;

import com.example.portfolio_api.dto.PostCreateRequest;
import com.example.portfolio_api.dto.PostDetailResponse;
import com.example.portfolio_api.dto.PostResponse;
import com.example.portfolio_api.entity.Post;
import com.example.portfolio_api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponse> getPosts() {
        return postRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostDetailResponse getPost(String slug) {
        Post post = postRepository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return PostDetailResponse.from(post);
    }

    public void create(PostCreateRequest request) {
        Post post = new Post(request.getSlug(), request.getTitle(), request.getSummary(), request.getContent());
        postRepository.save(post);
    }
}
