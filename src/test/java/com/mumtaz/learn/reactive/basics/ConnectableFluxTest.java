package com.mumtaz.learn.reactive.basics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

@RunWith(SpringRunner.class)
@AutoConfigureWebClient
public class ConnectableFluxTest {

//    @Autowired
//    WebClient webClient;

    @Test
    public void connectTest() {
        WebClient webClient = WebClient.create();

                webClient.get()
                        .uri("http://localhost:8080/hotstream")

                .exchange()
                .subscribe(System.out::println);


    }
}
