package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.domain.board.Dto.BoardResponseFindAllDto;
import com.board.board.domain.post.Post;
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
    @Autowired
    PostService postService;

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

        List<BoardResponseFindAllDto> all_to_dto = boardService.findAll_without_post();

        all_to_dto.forEach(dto -> System.out.println(dto.getId() + ", " + dto.getTitle()));
    }

    /***
     * join fetch test
     */
    @Test
    public void 단건게시판_단건게시글_조회(){
        //given
        Post post = Post.builder()
                .title("테스트 제목11")
                .author("테스트 작가11")
                .hit(100L)
                .build();
        postService.save(post);

        Board board = Board.builder()
                .title("테스트 게시판11")
                .build();
        boardService.save(board);

        postService.set_relation_with_Board(board.getId(), post.getId());

        // when
        Board findBoard = boardService.findOne_with_post(board.getId());

        //then
        System.out.println(findBoard.getTitle());
        findBoard.getPosts().forEach(
                foreach_post -> System.out.println(foreach_post.getTitle() + ", " + foreach_post.getAuthor() + ", " + foreach_post.getHit())
        );
    }

    /***
     * join fetch test
     */
    @Test
    public void 단건게시판_다수게시글_조회(){
        Board board = Board.builder()
                .title("테스트 게시판11")
                .build();
        boardService.save(board);

        //given
        for(int i=0; i<100; i++){
            String title = "junit테스트글" + Integer.toString(i);
            String author = "junit테스트작가" + Integer.toString(i);
            Long hit = Long.valueOf(i);

            Post post = Post.builder()
                    .title(title)
                    .author(author)
                    .hit(hit)
                    .build();
            postService.save(post);

            postService.set_relation_with_Board(board.getId(), post.getId());
        }


        // when
        Board findBoard = boardService.findOne_with_post(board.getId());

        //then
        System.out.println(findBoard.getTitle());
        findBoard.getPosts().forEach(
                foreach_post -> System.out.println(foreach_post.getTitle() + ", " + foreach_post.getAuthor() + ", " + foreach_post.getHit())
        );
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