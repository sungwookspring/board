package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.domain.board.Dto.BoardResponseFindAllDto;
import com.board.board.domain.board.Dto.BoardResponseFindoneWithPostDto;
import com.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board){
        return boardRepository.save(board).getId();
    }

    public Board findById(Long saveId) {
        Board findBoard = boardRepository.findById(saveId)
                .orElseThrow(
                        () -> new IllegalStateException("존재하지 않은 board")
                );

        return findBoard;
    }

    public List<BoardResponseFindAllDto> findAll_without_post() {
        List<Board> boards = boardRepository.findAll();

        List<BoardResponseFindAllDto> responseDtos = boards.stream()
                .map(board -> BoardResponseFindAllDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .build())
                .collect(Collectors.toList());

        return responseDtos;
    }

    public List<BoardResponseFindoneWithPostDto> find_posts_related_board(Long board_id){
        Board findBoard = boardRepository.findOneWithPost(board_id);

        List<BoardResponseFindoneWithPostDto> responses = findBoard.getPosts().stream()
                .map(post -> BoardResponseFindoneWithPostDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .hit(post.getHit())
                        .build())
                .collect(Collectors.toList());


        return responses;
    }
}
