package com.board.board.domain;

import com.board.board.domain.board.Board;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts")
    private Board board;

    /***
     * 연관관계 설정
     */
    public void set_relation_with_board(Board board){
        board.getPosts().add(this);
        this.board = board;
    }

    @Builder
    public Post(String title, String author, Long hit) {
        this.title = title;
        this.author = author;
        this.hit = hit;
    }
}
