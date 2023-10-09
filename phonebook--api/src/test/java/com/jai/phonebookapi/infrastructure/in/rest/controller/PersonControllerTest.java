package com.jai.phonebookapi.infrastructure.in.rest.controller;

import com.jai.api.infrastructure.in.rest.dto.PageablePersonDTO;
import com.jai.api.infrastructure.in.rest.dto.PersonDTO;
import com.jai.core.domain.model.Person;
import com.jai.core.providers.PersonProvider;
import com.jai.phonebookapi.application.usecase.in.PersonCreatorUseCase;
import com.jai.phonebookapi.application.usecase.in.PersonDeleterUseCase;
import com.jai.phonebookapi.application.usecase.out.PersonFetcherUseCase;
import com.jai.phonebookapi.infrastructure.in.rest.mapper.PersonDTOMapper;
import com.jai.phonebookapi.infrastructure.in.rest.mapper.PersonDTOMapperImpl;
import com.jai.phonebookapi.providers.PersonDTOProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonControllerTest {

    PersonController personController;
    PersonCreatorUseCase personCreatorUseCase;
    PersonFetcherUseCase personFetcherUseCase;
    PersonDeleterUseCase personDeleterUseCase;
    PersonDTOMapper personDTOMapper;

    @BeforeEach
    void setUp() {
        personDTOMapper = new PersonDTOMapperImpl();
        personCreatorUseCase = mock(PersonCreatorUseCase.class);
        personFetcherUseCase = mock(PersonFetcherUseCase.class);
        personDeleterUseCase = mock(PersonDeleterUseCase.class);
        personController = new PersonController(personCreatorUseCase, personFetcherUseCase, personDeleterUseCase, personDTOMapper);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonDTOProvider.class)
    void givenPersonDTOShouldCreatePerson(PersonDTO personDTO) {
        //GIVE
        when(personCreatorUseCase.createPerson(any(Person.class))).thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArguments()[0]));
        //WHEN
        Mono<PersonDTO> result = personController.createPerson(Mono.just(personDTO), null);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(personDTO)
                .verifyComplete();
        verify(personCreatorUseCase, times(1)).createPerson(any(Person.class));
    }

    @ParameterizedTest
    @MethodSource("com.jai.phonebookapi.providers.PersonProviders#provideModelAndDTO")
    void whenFindByIdShouldReturnPersonDTO(Person person, PersonDTO personDTO) {
        //GIVEN
        when(personFetcherUseCase.findById(anyInt())).thenAnswer(invocationOnMock -> Mono.just(person));
        //WHEN
        Mono<PersonDTO> result = personController.getPerson(1, null);
        //THEN
        StepVerifier.create(result)
                .expectNext(personDTO)
                .verifyComplete();
        verify(personFetcherUseCase, times(1)).findById(1);

    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void whenFindAllShouldReturnPageableDTO(Person person) {
        //GIVEN
        when(personFetcherUseCase.findAll()).thenAnswer(invocationOnMock -> Flux.just(person));
        //WHEN
        Mono<PageablePersonDTO> result = personController.getAllPersons(null);
        //THEN
        PageablePersonDTO expectedResult = new PersonDTOProvider().getPageablePersonDTO();
        StepVerifier.create(result)
                .expectNext(expectedResult)
                .verifyComplete();
        verify(personFetcherUseCase, times(1)).findAll();

    }

    @Test
    void whenDeletePersonByIdShouldDeletePerson() {
        // GIVEN
        when(personDeleterUseCase.deletePersonById(anyInt())).thenAnswer(invocationOnMock -> Mono.empty());
        //WHEN
        Mono<Void> result = personController.deletePerson(1, null);
        //THEN
        StepVerifier
                .create(result)
                .verifyComplete();
        verify(personDeleterUseCase, times(1)).deletePersonById(1);

    }
}
