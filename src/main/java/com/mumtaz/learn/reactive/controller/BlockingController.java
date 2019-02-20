package com.mumtaz.learn.reactive.controller;

import com.mumtaz.learn.reactive.domain.Quotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class BlockingController {

    @GetMapping("/blockingGet")
    public Quotation getQuotation () {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Quotation("Blcoking quote", 100D);
    }
}
