package com.mumtaz.learn.reactive.basics;


import org.junit.Test;
import reactor.core.publisher.Mono;

public class CreateFluxTest {

    @Test
    public void createMono() {
        Mono mono = Mono.just("first mono");

        mono.subscribe(System.out::println);
    }
}
