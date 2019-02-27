package com.mumtaz.learn.reactive.controller;

import com.mumtaz.learn.reactive.domain.Coffee;
import com.mumtaz.learn.reactive.repository.CoffeeRepository;
import com.mumtaz.learn.reactive.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class CoffeeController {

    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping("/coffee")
    public Flux<Coffee> getCoffee() {
        return coffeeRepository.findAll();
    }
}
