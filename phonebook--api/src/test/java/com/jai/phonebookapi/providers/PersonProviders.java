package com.jai.phonebookapi.providers;

import com.jai.core.providers.PersonProvider;
import org.junit.jupiter.params.provider.Arguments;


import java.util.List;
import java.util.stream.Stream;

public class PersonProviders {

    private static Stream<Arguments> provideModelAndDTO() {
        return Stream.of(Arguments.of(new PersonProvider().getPerson(), new PersonDTOProvider().getPersonDTO()));
    }

    private static Stream<Arguments> provideModelListAndPageableDTO() {
        return Stream.of(Arguments.of(List.of(new PersonProvider().getPerson()), new PersonDTOProvider().getPageablePersonDTO()));
    }

}
