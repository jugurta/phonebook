package com.jai.phonebookapi.infrastructure.in.rest.mapper;

import com.jai.api.infrastructure.in.rest.dto.PageablePersonDTO;
import com.jai.api.infrastructure.in.rest.dto.PersonDTO;
import com.jai.core.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

class PersonDTOMapperTest {

    PersonDTOMapper personDTOMapper;

    @BeforeEach
    void setUp() {
        personDTOMapper = new PersonDTOMapperImpl();
    }


    @ParameterizedTest
    @MethodSource("com.jai.phonebookapi.providers.PersonProviders#provideModelAndDTO")
    void givenPersonDTOShouldMapPersonModel(Person person, PersonDTO personDTO) {
        Assertions.assertEquals(personDTOMapper.toDomain(personDTO), person);
    }

    @ParameterizedTest
    @MethodSource("com.jai.phonebookapi.providers.PersonProviders#provideModelAndDTO")
    void givenPersonModelShouldMapPersonDTO(Person person, PersonDTO personDTO) {
        Assertions.assertEquals(personDTOMapper.toDTO(person), personDTO);
    }


    @ParameterizedTest
    @MethodSource("com.jai.phonebookapi.providers.PersonProviders#provideModelListAndPageableDTO")
    void givenListPersonModelShouldMapPageablePersonDTO(List<Person> personList, PageablePersonDTO pageablePersonDTO) {
        Assertions.assertEquals(personDTOMapper.toPageableDTO(personList), pageablePersonDTO);
    }
}
