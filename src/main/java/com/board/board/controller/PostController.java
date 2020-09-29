package com.board.board.controller;

import com.board.board.domain.board.Dto.BoardResponseFindAllDto;
import com.board.board.domain.post.Dto.PostResponseFindByIdDto;
import com.board.board.service.BoardService;
import com.board.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    @GetMapping("/post/{post_id}")
    public String list(@PathVariable("post_id") Long post_id, Model model){
        PostResponseFindByIdDto findPost = postService.findById_To_Dto(post_id);
        List<BoardResponseFindAllDto> boards = boardService.findAll_without_post();

        model.addAttribute("boards", boards);
        model.addAttribute("post", findPost);

        return "post/list";
    }
}
