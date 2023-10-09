package com.jai.core.domain.repository;

import com.jai.core.domain.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {

    Mono<Person> save(Person person);

    Mono<Person> findById(Integer id);

    Flux<Person> findAll();

    Mono<Void> deleteById(Integer id);
}
