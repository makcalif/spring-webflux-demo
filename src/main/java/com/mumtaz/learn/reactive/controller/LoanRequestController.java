package com.mumtaz.learn.reactive.controller;

import com.mumtaz.learn.reactive.domain.BestQuotationResponse;
import com.mumtaz.learn.reactive.domain.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanRequestController {

    Logger logger = LoggerFactory.getLogger(LoanRequestController.class);

    @GetMapping("/{bank}/quotation") // bank name format is bank-[1-9]
    public Mono<Quotation> quotation (final @PathVariable("bank") String bank,
                                      final @RequestParam(value="loanAmount", required = true)
                                              Double loanAmount) {
        char bankIndex = bank.charAt(5);

        double interestRate =  ((double) bankIndex) / 100d;
        logger.info("interest rate for bank {} is {}", bank, interestRate);

//        if (bankIndex == '2') {
//            return Mono.error(new ServiceUnavailableException("bank-" + bankIndex + " service is unavailable"));
//        }
//
//        if (bankIndex == '3') {
//            return Mono.delay(Duration.ofMillis(4000))
//                    .then(Mono.just(new Quotation("Bank-" + bankIndex, loanAmount)));
//        }

        return Mono.just(new Quotation("Bank-" + bankIndex, loanAmount * interestRate));
    }

    @GetMapping("/getBestQuotation")
    public Mono<BestQuotationResponse> getBestQuotation() {
        Flux<String> banksUrl = Flux.just("Bank-1", "Bank-2", "Bank-3", "Bank-4", "Bank-5", "Bank-6");
        Double loanAmount = 1000d;

        return Flux.from(banksUrl)
                .flatMap(bankUrl -> {
                    Mono<Quotation> mq = requestForQuotation(bankUrl, loanAmount);  // scatter
                    return mq;
                })
                .collect(() -> new BestQuotationResponse(loanAmount), BestQuotationResponse::offer) // gather
                .doOnSuccess(BestQuotationResponse::finish)
                .flatMap(bqr -> {
                    return Mono.justOrEmpty(selectBestQuotation(bqr.getOffers()))
                            .map(bestQuotation -> {
                                bqr.bestOffer(bestQuotation);
                                return bqr;
                            });
                })
                .single();
    }

    private Optional<Quotation> selectBestQuotation(List<Quotation> quotations){
        return  Optional.ofNullable(quotations)
                .flatMap( _quotations -> _quotations.stream()
                        .sorted((q1, q2) -> (q1.getInterestCharged() > q2.getInterestCharged() ? 1:-1))
                        .findFirst());
    }

    public Mono<Quotation>  requestForQuotation(String bankUrl, Double loanAmount) {

        return WebClient.create().get()
                .uri(builder -> builder.scheme("http")
                        .port(8080)
                        .host("localhost").path(bankUrl + "/quotation")
                        .queryParam("loanAmount", loanAmount)
                        .build())
                .retrieve()
                .bodyToMono(Quotation.class);
    }
}
