package com.example.carmanager.v2.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users/")
public class UserViewController {

    @GetMapping("/join")
    public String join() { return "join"; }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PutMapping("/logout")
    public String logout(){
        return "/";
    }
}
