package com.mumtaz.learn.reactive.basics;

import org.junit.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ConnectableFluxTest {

    @Test
    public void testHandleErrorsHotStreamWithOnErrorContinue() {
        // work on this later
        ConnectableFlux<Long> longStream = Flux.<Long>create(longFluxSink -> {
            while(true) {
                longFluxSink.next(System.currentTimeMillis());
            }
        }).publish();

        longStream.subscribe(System.out::println);

        longStream.connect();
    }
}
