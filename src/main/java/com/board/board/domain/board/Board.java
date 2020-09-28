package com.board.board.domain.board;

import com.board.board.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***
 * 게시판 엔티티
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "bbs_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Board(String title) {
        this.title = title;
    }
}
