package com.board.board.domain.board.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseFindoneWithPostDto {
    private Long id;
    private String title;
    private Long hit;

    @Builder
    public BoardResponseFindoneWithPostDto(Long id, String title, Long hit) {
        this.id = id;
        this.title = title;
        this.hit = hit;
    }
}
