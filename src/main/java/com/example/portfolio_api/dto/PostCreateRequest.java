package com.example.portfolio_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateRequest {
    private String slug;
    private String title;
    private String summary;
    private String content;
}
