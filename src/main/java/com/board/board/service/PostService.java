package com.board.board.service;

import com.board.board.domain.Post;
import com.board.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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
}
