package com.mumtaz.learn.reactive.basics;


import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

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

    @Test
    public void createFluxAndVerify() {
        Flux flux = Flux.just("A", "B", "C");
        StepVerifier.create(flux)
                .expectNext("A")
                .expectNext("B")
                .expectNext("C")
                .expectComplete()
                .verify();
    }

    @Test
    public void createFluxFromArray() {
        Flux flux = Flux.fromArray("I love to code".split(" "));
        StepVerifier.create(flux)
                .expectNext("I")
                .expectNext("love")
                .expectNext("to")
                .expectNext("code")
                .expectComplete()
                .verify();
    }

    @Test
    public void createFluxFromIterable() {
        Flux flux = Flux.fromIterable(Arrays.asList("I", "love", "to", "code"));
        StepVerifier.create(flux)
                .expectNext("I")
                .expectNext("love")
                .expectNext("to")
                .expectNext("code")
                .expectComplete()
                .verify();
    }
}
