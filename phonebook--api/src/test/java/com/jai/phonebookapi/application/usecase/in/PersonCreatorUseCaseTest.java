package com.jai.phonebookapi.application.usecase.in;

import com.jai.core.domain.model.Person;
import com.jai.core.domain.repository.PersonRepository;
import com.jai.core.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonCreatorUseCaseTest {

    PersonCreatorUseCase personCreatorUseCase;
    PersonRepository personRepository;


    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        personCreatorUseCase = new PersonCreatorUseCase(personRepository);
    }


    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenPersonToSaveShouldReturnPersonModel(Person person) {
        //GIVEN
        when(personRepository.save(any(Person.class))).thenAnswer(invocationOnMock -> Mono.just(person));
        //WHEN
        Mono<Person> result = personCreatorUseCase.createPerson(person);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
        verify(personRepository, times(1)).save(person);
    }

}
