package com.thoughtworks.nho.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/greeting")
@RestController
public class GreetingController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String greet() {
        return "Hello, Spring MVC";
    }
}
