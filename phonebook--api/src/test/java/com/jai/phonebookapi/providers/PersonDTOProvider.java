package com.jai.phonebookapi.providers;

import com.jai.api.infrastructure.in.rest.dto.PageablePersonDTO;
import com.jai.api.infrastructure.in.rest.dto.PersonDTO;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class PersonDTOProvider implements ArgumentsProvider {

    PersonDTO personDTO = new PersonDTO(1, "John", "Smith", LocalDate.of(2000, 10, 20));

    PageablePersonDTO pageablePersonDTO = new PageablePersonDTO(1, List.of(personDTO));

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public PageablePersonDTO getPageablePersonDTO() {
        return pageablePersonDTO;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(Arguments.of(personDTO));
    }
}
