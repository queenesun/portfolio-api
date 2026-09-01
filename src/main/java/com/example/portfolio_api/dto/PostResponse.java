package com.example.portfolio_api.dto;

import com.example.portfolio_api.entity.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String slug;
    private String title;
    private String summary;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public static PostResponse from(Post post) {
        return new PostResponse(
            post.getId(), post.getSlug(), post.getTitle(), post.getSummary(), post.getCreatedAt()
        );
    }
}
