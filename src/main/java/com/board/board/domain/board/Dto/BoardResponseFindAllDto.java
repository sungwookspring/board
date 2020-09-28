package com.board.board.domain.board.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseFindAllDto {
    private Long id;
    private String title;

    @Builder
    public BoardResponseFindAllDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
