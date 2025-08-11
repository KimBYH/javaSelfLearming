package com.example.javaSelfLearming.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String test(){
        return "hello";
    }
    @GetMapping("/hello")
    public String hello(){
        return "helloworld";
    }
}
