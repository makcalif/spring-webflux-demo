package com.mumtaz.learn.reactive.controller;

import com.mumtaz.learn.reactive.domain.Event;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class EventsController {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(name = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> getUnlimitedEvents () {
        Flux events = Flux.generate(sink -> sink.next(getNextEvent()));
        Flux<Event> eventsWithDelay =
                events.zipWith(Flux.interval(Duration.ofSeconds(2)),
                        (event, delay) -> event);
        return eventsWithDelay;
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
