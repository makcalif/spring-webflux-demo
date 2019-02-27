package com.mumtaz.learn.reactive.repository;

import com.mumtaz.learn.reactive.domain.Coffee;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CoffeeRepository extends ReactiveCrudRepository<Coffee, Long> {
    @Query("DELETE FROM coffee")
    Mono<Coffee> deleteAllById();
}