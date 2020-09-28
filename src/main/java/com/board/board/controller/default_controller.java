package com.board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class default_controller {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
