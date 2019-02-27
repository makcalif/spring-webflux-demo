package com.mumtaz.learn.reactive.respository;
import com.mumtaz.learn.reactive.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    @Tailable
    Flux<User> findUsersBy();

//    @Tailable
//    @Override
//    Flux<User> findAll();
}
