package com.mumtaz.learn.reactive;

import com.mumtaz.learn.reactive.service.BackPressureDemoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories("com.mumtaz.learn.reactive.repository")
public class SpringWebFluxDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxDemo.class, args);
    }

    //@Component
    private class MyRunner implements CommandLineRunner {

        @Autowired
        BackPressureDemoClient webClientTransport;

        @Override
        public void run(String... args) throws Exception {
            webClientTransport.doGet();
        }
    }
}
