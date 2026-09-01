package com.example.portfolio_api.controller;

import com.example.portfolio_api.dto.PostCreateRequest;
import com.example.portfolio_api.dto.PostDetailResponse;
import com.example.portfolio_api.dto.PostResponse;
import com.example.portfolio_api.entity.Post;
import com.example.portfolio_api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable String slug) {
        return ResponseEntity.ok(postService.getPost(slug));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostCreateRequest request) {
        postService.create(request);
        return ResponseEntity.status(201).build();
    }
}
