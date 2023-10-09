package com.jai.phonebookapi.application.usecase.in;


import com.jai.core.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class PersonDeleterUseCase {

    private final PersonRepository personRepository;

    public Mono<Void> deletePersonById(Integer id) {
        return personRepository.deleteById(id);
    }
}
