package com.jai.core.infrastructure.out.persistence.mongodb.adapter;

import com.jai.core.domain.model.Person;
import com.jai.core.infrastructure.out.persistence.mongodb.entity.PersonEntity;
import com.jai.core.infrastructure.out.persistence.mongodb.mapper.PersonMongoMapper;
import com.jai.core.infrastructure.out.persistence.mongodb.mapper.PersonMongoMapperImpl;
import com.jai.core.infrastructure.out.persistence.mongodb.repository.ReactivePersonMongoRepository;
import com.jai.core.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonMongoRepositoryAdapterTest {
    ReactivePersonMongoRepository reactiveMongoPersonRepository;
    PersonMongoMapper mongoPersonMapper;
    PersonMongoRepositoryAdapter personRepositoryAdapter;


    @BeforeEach
    void setUp() {
        mongoPersonMapper = new PersonMongoMapperImpl();
        reactiveMongoPersonRepository = mock(ReactivePersonMongoRepository.class);
        personRepositoryAdapter = new PersonMongoRepositoryAdapter(reactiveMongoPersonRepository, mongoPersonMapper);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenPersonModelShouldSaveAndReturnPersonModel(Person person) {
        //GIVEN
        when(reactiveMongoPersonRepository.save(any(PersonEntity.class))).thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArguments()[0]));
        //WHEN
        Mono<Person> result = personRepositoryAdapter.save(person);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
    }

    @ParameterizedTest
    @MethodSource("com.jai.core.providers.PersonProviders#provideModelAndEntity")
    void givenIdentifierShouldReturnPersonModel(Person person, PersonEntity personEntity) {
        //GIVEN
        when(reactiveMongoPersonRepository.findById(anyInt())).thenAnswer(invocationOnMock -> Mono.just(personEntity));
        //WHEN
        Mono<Person> result = personRepositoryAdapter.findById(1);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
    }

    @ParameterizedTest
    @MethodSource("com.jai.core.providers.PersonProviders#provideModelAndEntity")
    void givenFindAllShouldReturnFluxOfPersonModel(Person person, PersonEntity personEntity) {
        //GIVEN
        when(reactiveMongoPersonRepository.findAll()).thenAnswer(invocationOnMock -> Flux.just(personEntity));
        //WHEN
        Flux<Person> result = personRepositoryAdapter.findAll();
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
    }

    @Test
    void givenIdShouldDeletePerson() {
        // GIVEN
        when(reactiveMongoPersonRepository.deleteById(anyInt())).thenAnswer(invocationOnMock -> Mono.empty());
        //WHEN
        Mono<Void> result = personRepositoryAdapter.deleteById(1);
        //THEN
        StepVerifier
                .create(result)
                .verifyComplete();
    }

}
