package com.board.board.domain;

import javax.persistence.*;

/***
 * 게시글
 */
@Entity
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
}
