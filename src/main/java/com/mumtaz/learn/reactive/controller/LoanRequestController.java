package com.mumtaz.learn.reactive.controller;

import com.mumtaz.learn.reactive.domain.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.time.Duration;

@RestController
public class LoanRequestController {

    Logger logger = LoggerFactory.getLogger(LoanRequestController.class);

    @GetMapping("/test/{bank}/quotation") // bank name format is bank-[1-9]
    public Mono<Quotation> quotation (final @PathVariable("bank") String bank,
                                      final @RequestParam(value="loanAmount", required = true)
                                              Double loanAmount) {
        char bankIndex = bank.charAt(5);

        double interestRate =  ((double) bankIndex) / 100d;
        logger.info("interest rate for bank {} is {}", bank, interestRate);

        if (bankIndex == '2') {
            return Mono.error(new ServiceUnavailableException("bank-" + bankIndex + " service is unavailable"));
        }

        if (bankIndex == '3') {
            return Mono.delay(Duration.ofMillis(4000))
                    .then(Mono.just(new Quotation("Bank-" + bankIndex, loanAmount)));
        }

        return Mono.just(new Quotation("Bank-" + bankIndex, loanAmount * interestRate));
    }
}
