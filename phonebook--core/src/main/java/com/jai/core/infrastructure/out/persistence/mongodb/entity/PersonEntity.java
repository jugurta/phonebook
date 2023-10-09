package com.jai.core.infrastructure.out.persistence.mongodb.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "person")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor

public class PersonEntity implements Serializable {

    @Id
    private Integer id;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    @Field("birth_date")
    private LocalDate birthDate;
}