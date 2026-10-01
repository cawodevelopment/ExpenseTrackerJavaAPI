package com.io.github.cawodevelopment.expense_tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorldController {

    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }
}
