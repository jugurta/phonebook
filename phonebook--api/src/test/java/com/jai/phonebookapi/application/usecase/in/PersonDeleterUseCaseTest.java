package com.jai.phonebookapi.application.usecase.in;

import com.jai.core.domain.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class PersonDeleterUseCaseTest {

    PersonDeleterUseCase personDeleterUseCase;
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        personDeleterUseCase = new PersonDeleterUseCase(personRepository);
    }


    @Test
    void whenDeletePersonByIdShouldDeletePerson() {
        // GIVEN
        when(personRepository.deleteById(anyInt())).thenAnswer(invocationOnMock -> Mono.empty());
        // WHEN
        Mono<Void> result = personDeleterUseCase.deletePersonById(2);
        //THEN
        StepVerifier
                .create(result)
                .verifyComplete();
        verify(personRepository, times(1)).deleteById(2);
    }

}
