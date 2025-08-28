package com.example.javaSelfLearming.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstApiController {

    @GetMapping("/hello")
    public String hello() {
        return "articles/show";
    }
}
