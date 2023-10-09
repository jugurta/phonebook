package com.jai.phonebookapi.application.usecase.in;

import com.jai.core.domain.model.Person;
import com.jai.core.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class PersonCreatorUseCase {
    private final PersonRepository personRepository;

    public Mono<Person> createPerson(Person person) {
        return personRepository.save(person);
    }
}