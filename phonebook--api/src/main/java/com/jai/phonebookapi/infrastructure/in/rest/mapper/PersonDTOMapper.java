package com.jai.phonebookapi.infrastructure.in.rest.mapper;

import com.jai.api.infrastructure.in.rest.dto.PageablePersonDTO;
import com.jai.api.infrastructure.in.rest.dto.PersonDTO;
import com.jai.core.domain.model.Person;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonDTOMapper {
    Person toDomain(PersonDTO personDTO);

    PersonDTO toDTO(Person person);

    List<PersonDTO> toDTO(List<Person> personList);

    default PageablePersonDTO toPageableDTO(List<Person> personList) {
        return new PageablePersonDTO(personList.size(), toDTO(personList));
    }
}
