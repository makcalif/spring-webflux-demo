package com.mumtaz.learn.reactive.service;

import com.mumtaz.learn.reactive.subscriber.CustomBackpressureSubscriber;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BackPressureDemoClient {

    private final WebClient webClient;

    public BackPressureDemoClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_EVENT_STREAM_VALUE)
                .baseUrl("http://localhost:8080/getInfinite")
                .build();

    }

    public  void doGet() {

        CustomBackpressureSubscriber myCustomBackpressureSubscriber = new CustomBackpressureSubscriber();

        this.webClient.get()
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(myCustomBackpressureSubscriber);
    }
}

