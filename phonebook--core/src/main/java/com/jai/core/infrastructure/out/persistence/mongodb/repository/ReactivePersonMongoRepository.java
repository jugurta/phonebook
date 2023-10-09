package com.jai.core.infrastructure.out.persistence.mongodb.repository;

import com.jai.core.infrastructure.out.persistence.mongodb.entity.PersonEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactivePersonMongoRepository extends ReactiveMongoRepository<PersonEntity, Integer> {
}