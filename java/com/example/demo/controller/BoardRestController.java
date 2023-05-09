package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {

    @GetMapping("/hello")
    public String[] hello() {
        return new String[] {"Hello", "World"};
    }
}
