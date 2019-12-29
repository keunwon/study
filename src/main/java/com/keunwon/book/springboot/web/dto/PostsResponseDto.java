package com.keunwon.book.springboot.web.dto;

import com.keunwon.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTittle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}
