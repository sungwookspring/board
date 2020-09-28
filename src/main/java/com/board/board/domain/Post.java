package com.board.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/***
 * 게시글
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String author;

    private Long hit;

    @OneToMany(mappedBy = "posts")
    private Board board;

    @Builder
    public Post(String title, String author, Long hit) {
        this.title = title;
        this.author = author;
        this.hit = hit;
    }
}
