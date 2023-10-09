package com.jai.phonebookapi.application.usecase.out;

import com.jai.core.domain.model.Person;
import com.jai.core.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonFetcherUseCase {

    private final PersonRepository personRepository;

    public Mono<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

}