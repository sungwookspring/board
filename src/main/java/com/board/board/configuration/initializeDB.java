package com.board.board.configuration;

import com.board.board.domain.board.Board;
import com.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initializeDB {
    private final BoardService boardService;

    @PostConstruct
    public void run(){
        do_initialize();
    }

    private void do_initialize(){
        for(int i=1; i<=100; i++){
            String title = "테스트용" + Integer.toString(i);
            create_board(title);
        }
    }

    private Board create_board(String title){
        Board board = Board.builder()
                .title(title)
                .build();

        Long saveId = boardService.save(board);

        return board;
    }
}
