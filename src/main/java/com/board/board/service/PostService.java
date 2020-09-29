package com.board.board.service;

import com.board.board.domain.post.Dto.PostResponseFindByIdDto;
import com.board.board.domain.post.Post;
import com.board.board.domain.board.Board;
import com.board.board.repository.BoardRepository;
import com.board.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Post post){
        return postRepository.save(post).getId();
    }

    public Post findById(Long id){
        Post findPost = postRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("존재하지 않은 post")
                );

        return  findPost;
    }

    @Transactional
    public void set_relation_with_Board(Long board_id, Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(
                        () -> new IllegalStateException("존재하지 않은 post")
                );

        Board board = boardRepository.findById(board_id)
                .orElseThrow(
                        () -> new IllegalStateException("존재하지 않은 board")
                );

        post.set_relation_with_board(board);
    }

    /***
     * ID기준 게시글 조회하고 Dto리턴
     */
    public PostResponseFindByIdDto findById_To_Dto(Long id){
        Post findPost = postRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("존재하지 않은 post")
                );

        return PostResponseFindByIdDto.builder()
                .id(findPost.getId())
                .title(findPost.getTitle())
                .author(findPost.getAuthor())
                .content(findPost.getContent())
                .hit(findPost.getHit())
                .build();
    }
}
