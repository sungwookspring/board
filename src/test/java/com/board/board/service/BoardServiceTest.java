package com.board.board.service;

import com.board.board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardServiceTest {
    @Autowired
    BoardService boardService;

    @Test
    public void 게시판생성(){
        //given
        Board board = Board.builder()
                .title("테스트 게시판")
                .build();

        //when
        Long saveId = boardService.save(board);

        //then
        Board findBoard = boardService.findById(saveId);

        Assertions.assertThat(findBoard.getTitle()).isEqualTo(board.getTitle());
    }
}