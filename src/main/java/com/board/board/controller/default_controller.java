package com.board.board.controller;

import com.board.board.domain.board.Dto.BoardResponseFindAllDto;
import com.board.board.service.BoardService;
import com.board.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class default_controller {
    private final BoardService boardService;
    private final PostService postService;

    @GetMapping("/")
    public String index(Model model){
        List<BoardResponseFindAllDto> boards = boardService.findAll_to_Dto();
        model.addAttribute("boards", boards);
        return "index";
    }
}
