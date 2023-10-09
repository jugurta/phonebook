package com.jai.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}