package com.board.board.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***
 * 게시판 엔티티
 */
@Entity
@Table(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "bbs_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<Post> posts = new ArrayList<>();
}
