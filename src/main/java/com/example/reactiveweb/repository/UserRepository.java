package com.example.reactiveweb.repository;

import com.example.reactiveweb.entity.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.lang.model.element.Name;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users,Long> {
     Mono<Users> findByName(Mono<String> name);
}
