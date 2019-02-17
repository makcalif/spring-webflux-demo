package com.mumtaz.learn.reactive.basics;


import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreateFluxTest {

    @Test
    public void createMono() {
        Mono mono = Mono.just("first mono");

        mono.subscribe(System.out::println);
    }

    @Test
    public void createFlux() {
        Flux flux = Flux.just("A", "B", "C");
        flux.subscribe(System.out::println);    
    }
}
