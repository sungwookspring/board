package com.board.board.service;


import com.board.board.domain.board.Board;
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
public class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    BoardService boardService;

    @Test
    public void 게시글생성(){
        //given
        Post post = create_post("테스트 타이틀", "테스트 작가", 1L);
    }

    @Test
    public void 게시판생성_게시글생성_연결(){
        //given
        Post post = create_post("테스트 타이틀", "테스트 작가", 1L);
        Board board = Board.builder()
                .title("테스트 게시판")
                .build();
        boardService.save(board);

        //when
        post.set_relation_with_board(board);

        //then
        List<Post> posts = board.getPosts();
        Post first_post = posts.get(0);

        Assertions.assertThat(first_post.getTitle()).isEqualTo(post.getTitle());
        Assertions.assertThat(first_post.getAuthor()).isEqualTo(post.getAuthor());
        Assertions.assertThat(first_post.getHit()).isEqualTo(post.getHit());
    }

    private Post create_post(String title, String author, Long hit){
        Post post = Post.builder()
                .title(title)
                .author(author)
                .hit(hit)
                .build();

        Long saveId = postService.save(post);

        // 저장 검증
        Post findPost = postService.findById(saveId);
        Assertions.assertThat(findPost.getAuthor()).isEqualTo(post.getAuthor());
        Assertions.assertThat(findPost.getTitle()).isEqualTo(post.getTitle());
        Assertions.assertThat(findPost.getHit()).isEqualTo(post.getHit());

        return post;
    }

}