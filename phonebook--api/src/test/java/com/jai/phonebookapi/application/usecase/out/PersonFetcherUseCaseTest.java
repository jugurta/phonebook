package com.jai.phonebookapi.application.usecase.out;

import com.jai.core.domain.model.Person;
import com.jai.core.domain.repository.PersonRepository;
import com.jai.core.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class PersonFetcherUseCaseTest {

    PersonRepository personRepository;
    PersonFetcherUseCase personFetcherUseCase;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        personFetcherUseCase = new PersonFetcherUseCase(personRepository);
    }


    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenFetchPersonByIdShouldReturnPerson(Person person) {
        //GIVEN
        when(personRepository.findById(anyInt())).thenAnswer(invocationOnMock -> Mono.just(person));
        //WHEN
        Mono<Person> result = personFetcherUseCase.findById(1);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
        verify(personRepository, times(1)).findById(1);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenFetchAllShouldReturnAllPersons(Person person) {
        //GIVEN
        when(personRepository.findAll()).thenAnswer(invocationOnMock -> Flux.just(person));
        //WHEN
        Flux<Person> result = personFetcherUseCase.findAll();
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
        verify(personRepository, times(1)).findAll();

    }
}
