package com.board.board.controller;

import com.board.board.domain.board.Dto.BoardResponseFindAllDto;
import com.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/list/{board_id}")
    public String index(@PathVariable("board_id") Long board_id, Model model){
        List<BoardResponseFindAllDto> boards = boardService.findAll_without_post();

        model.addAttribute("boards", boards);
        return "index";
    }
}
