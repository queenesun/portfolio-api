package com.example.portfolio_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter // 조회만 하므로 Setter 필요 없음
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false)
    private String title;

    private String summary;

    @Column(columnDefinition = "TEXT") // content는 varchar 기본 255 넘김 -> TEXT
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Post(String slug, String title, String summary, String content) {
        this.slug = slug;
        this.title = title;
        this.summary = summary;
        this.content = content;
    }
}
