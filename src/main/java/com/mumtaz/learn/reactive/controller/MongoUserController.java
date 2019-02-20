package com.mumtaz.learn.reactive.controller;

import com.mumtaz.learn.reactive.domain.User;
import com.mumtaz.learn.reactive.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MongoUserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userRepository.findAll();
    }
}
