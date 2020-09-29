package com.board.board.configuration;

import com.board.board.domain.post.Post;
import com.board.board.domain.board.Board;
import com.board.board.service.BoardService;
import com.board.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initializeDB {
    private final BoardService boardService;
    private final PostService postService;

    @PostConstruct
    public void run(){
        do_initialize();
    }

    private void do_initialize(){
        Long[] boardIds = {0L ,0L};

        // 게시판 초기화
        for(int i=1; i<=2; i++){
            String title = "테스트용" + Integer.toString(i);
            Board board = create_board(title);
            boardIds[i-1] = board.getId();
        }

        // 게시판-게시글 연결
        Board board1 = boardService.findById(boardIds[0]);
        Board board2 = boardService.findById(boardIds[1]);

        // 게시글 초기화
        for(int i=1; i<=50; i++){
            String title = "테스트용" + Integer.toString(i);
            String author = "테스트작가" + Integer.toString(i);
            String content = "A".repeat(500);
            Long hit = Long.valueOf(i);
            Post post = create_post(title, author, content, hit);

            postService.set_relation_with_Board(board1.getId(),post.getId());
        }

        for(int i=50; i<=100; i++){
            String title = "테스트용" + Integer.toString(i);
            String author = "테스트작가" + Integer.toString(i);
            String content = "B".repeat(500);
            Long hit = Long.valueOf(i);
            Post post = create_post(title, author, content, hit);

            postService.set_relation_with_Board(board2.getId(),post.getId());
        }
    }

    private Board create_board(String title){
        Board board = Board.builder()
                .title(title)
                .build();

        Long saveId = boardService.save(board);

        return board;
    }

    private Post create_post(String title, String author, String content, Long hit){
        Post post = Post.builder()
                .title(title)
                .author(author)
                .content(content)
                .hit(hit)
                .build();

        Long saveId = postService.save(post);

        return post;
    }
}
