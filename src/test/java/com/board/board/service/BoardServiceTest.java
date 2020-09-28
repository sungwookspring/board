package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.domain.board.Dto.BoardResponseFindAllDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardServiceTest {
    @Autowired
    BoardService boardService;

    @Test
    public void 게시판생성(){
        create_board("테스트 게시판");
    }

    @Test
    public void 게시판다수_조회(){
        for(int i=0; i<=100; i++){
            String title = "테스트 게시판" + Integer.toString(i);
            create_board(title);
        }

        List<BoardResponseFindAllDto> all_to_dto = boardService.findAll_to_Dto();

        all_to_dto.forEach(dto -> System.out.println(dto.getId() + ", " + dto.getTitle()));
    }

    @Test
    public void 중복생성_테스트(){

    }

    private Board create_board(String title){
        Board board = Board.builder()
                .title(title)
                .build();
        Long saveId = boardService.save(board);

        //검증
        Board findBoard = boardService.findById(saveId);
        Assertions.assertThat(findBoard.getTitle()).isEqualTo(board.getTitle());

        return board;
    }
}