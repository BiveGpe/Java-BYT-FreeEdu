package com.byt.freeEdu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class ViewController {
    @GetMapping("/view/login")
    public Mono<String> login() {
        return Mono.just("login");
    }

    @GetMapping("/view/homepage")
    public Mono<String> homepage() {
        return Mono.just("homepage");
    }
}
