package com.keunwon.book.springboot.domain.posts;

import com.keunwon.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String tittle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String tittle, String content, String author) {
        this.tittle = tittle;
        this.content = content;
        this.author = author;
    }

    public void update(String tittle, String content) {
        this.tittle = tittle;
        this.content = content;
    }
}
