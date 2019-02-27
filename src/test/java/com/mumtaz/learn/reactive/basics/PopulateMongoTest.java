package com.mumtaz.learn.reactive.basics;

import com.mumtaz.learn.reactive.domain.User;
import com.mumtaz.learn.reactive.respository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PopulateMongoTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void addUser() {
        userRepository.save(new User(UUID.randomUUID().toString(), "random"));
    }
}
