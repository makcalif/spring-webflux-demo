package com.mumtaz.learn.reactive.controller;

import com.mumtaz.learn.reactive.domain.Event;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class HotStreamController {

    @GetMapping(value = "/hotstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ConnectableFlux<Event> getHotStream () {

          //ConnectableFlux<Event> connectableFlux = Flux

        ConnectableFlux<Event> eventStream = Flux.<Event>create(longFluxSink -> {
             getNextEvent();
        }).delaySequence(Duration.ofSeconds(2))
        .publish();

         return eventStream;

        //return eventStream.zipWith(delay, (event, dly) -> event);
    }

    private Event getNextEvent() {
        List<String> eventTitles = Arrays.asList("Team building",
                "New employee orientation",
                "Lunch and learn",
                "Holiday Party",
                "Cloudfoundry training");

        int next = ThreadLocalRandom.current().nextInt(0, eventTitles.size());
        Event event = new Event(eventTitles.get(next), new Date());
        return event;
    }
}
