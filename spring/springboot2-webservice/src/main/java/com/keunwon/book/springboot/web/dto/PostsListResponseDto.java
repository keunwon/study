package com.keunwon.book.springboot.web.dto;

import com.keunwon.book.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        this.modifiedDate = posts.getModifiedDate();
    }
}
