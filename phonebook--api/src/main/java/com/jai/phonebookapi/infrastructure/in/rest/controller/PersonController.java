package com.jai.phonebookapi.infrastructure.in.rest.controller;


import com.jai.api.infrastructure.in.rest.dto.PageablePersonDTO;
import com.jai.api.infrastructure.in.rest.dto.PersonDTO;
import com.jai.infrastructure.in.rest.controller.PersonApi;
import com.jai.phonebookapi.application.usecase.in.PersonCreatorUseCase;
import com.jai.phonebookapi.application.usecase.in.PersonDeleterUseCase;
import com.jai.phonebookapi.application.usecase.out.PersonFetcherUseCase;
import com.jai.phonebookapi.infrastructure.in.rest.mapper.PersonDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class PersonController implements PersonApi {

    private final PersonCreatorUseCase personCreatorUseCase;
    private final PersonFetcherUseCase personFetcherUseCase;
    private final PersonDeleterUseCase personDeleterUseCase;
    private final PersonDTOMapper personDTOMapper;


    @Override
    public Mono<PersonDTO> createPerson(Mono<PersonDTO> personDTOMono, ServerWebExchange exchange) {
        return personDTOMono.map(personDTOMapper::toDomain)
                .flatMap(personCreatorUseCase::createPerson)
                .map(personDTOMapper::toDTO);
    }

    @Override
    public Mono<Void> deletePerson(Integer id, ServerWebExchange exchange) {
        return personDeleterUseCase.deletePersonById(id);
    }

    @Override
    public Mono<PageablePersonDTO> getAllPersons(ServerWebExchange exchange) {
        return personFetcherUseCase.findAll().collectList()
                .map(personDTOMapper::toPageableDTO);
    }

    @Override
    public Mono<PersonDTO> getPerson(Integer id, ServerWebExchange exchange) {
        return personFetcherUseCase.findById(id).map(personDTOMapper::toDTO);
    }
}
