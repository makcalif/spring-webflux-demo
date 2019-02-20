package com.mumtaz.learn.reactive;

import com.mumtaz.learn.reactive.service.BackPressureDemoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringWebFluxDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxDemo.class, args);
    }

    @Component
    private class MyRunner implements CommandLineRunner {

        @Autowired
        BackPressureDemoClient webClientTransport;

        @Override
        public void run(String... args) throws Exception {
            webClientTransport.doGet();
        }
    }
}
