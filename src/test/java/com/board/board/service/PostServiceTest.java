package com.board.board.service;


import com.board.board.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceTest {
    @Autowired
    PostService postService;

    @Test
    public void 포스트생성(){
        //given
        Post post = Post.builder()
                .title("테스트 타이틀")
                .author("테스트 작가")
                .hit(1L)
                .build();

        //when
        Long saveId = postService.save(post);

        //then
        Post findPost = postService.findById(saveId);

        Assertions.assertThat(findPost.getAuthor()).isEqualTo(post.getAuthor());
        Assertions.assertThat(findPost.getTitle()).isEqualTo(post.getTitle());
        Assertions.assertThat(findPost.getHit()).isEqualTo(post.getHit());
    }

}