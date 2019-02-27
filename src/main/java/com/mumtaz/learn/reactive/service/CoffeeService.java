package com.mumtaz.learn.reactive.service;

import com.mumtaz.learn.reactive.repository.CoffeeRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.mumtaz.learn.reactive.domain.Coffee;

import javax.annotation.PostConstruct;

@Configuration
public class CoffeeService {
}


@Component
class DataLoader {

    @Autowired
    private  CoffeeRepository repo;

//    DataLoader(CoffeeRepository repo) {
//        this.repo = repo;
//    }

    @PostConstruct
    private void load() {
        repo.deleteAllById().thenMany(
                Flux.just("Peet's Coffee", "Philz Coffee", "Blue Bottle Coffee")
                        .map(Coffee::new)
                        .flatMap(repo::save))
                .thenMany(repo.findAll())
                .subscribe(System.out::println);
    }
}

@Configuration
class DatabaseConfig {
    @Bean
    PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .database("postgres")
                        .username("postgres")
                        .password("caffeine")
                        .build());
    }

    @Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();
    }

    @Bean
    R2dbcRepositoryFactory repositoryFactory(DatabaseClient client) {
        RelationalMappingContext context = new RelationalMappingContext();
        context.afterPropertiesSet();

        return new R2dbcRepositoryFactory(client, context);
    }
}

