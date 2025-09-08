package com.example.javaSelfLearming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/")
    public String test(){
        return "hello";
    }
    @GetMapping("/hello")
    public String hello(){
        return "helloworld";
    }
    @GetMapping("/testing")
    public String hi(){
        return "hello";
    }
    @GetMapping("/button")
    public String button(){
        return "btn";
    }
    @GetMapping("/list")
    public String list(){
        return "list";
    }
}
