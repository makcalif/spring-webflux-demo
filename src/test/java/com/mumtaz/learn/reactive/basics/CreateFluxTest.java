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

    @Test
    public void createFluxFromStream() {
        Flux flux = Flux.fromStream(Arrays.asList("I", "love", "to", "ocde").stream());
        StepVerifier.create(flux)
                .expectNext("I")
                .expectNext("love")
                .expectNext("to")
                .expectNext("code")
                .expectComplete()
                .verify();
    }

    @Test
    public void testOnErrorContinue() {

        Flux<String> flux = Flux.<String>fromIterable(Arrays.asList("1", "2", "a", "3", "4"));
        flux.map(v -> Integer.parseInt(v))
                .onErrorContinue( (err, sourceObj) -> {
                    System.out.println( "intObj:" + sourceObj);  // this is "a"
                    System.out.println( "logging error:" + err);
                })
                .subscribe(System.out::println,
                    e -> {
                        System.out.println("should not print out error here :" +e);
                    },
                    () -> System.out.println("complete")
                );
    } 

}
