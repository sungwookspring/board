package com.board.board.domain.post.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseFindByIdDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Long hit;

    @Builder
    public PostResponseFindByIdDto(Long id, String title, String author, String content, Long hit) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.hit = hit;
    }
}
