package com.stay_fine.mapper;

import com.stay_fine.controller.request.PersonRequest;
import com.stay_fine.controller.response.PersonResponse;
import com.stay_fine.model.entity.Person;

public class PersonMapper {

    public static Person personRequestToEntity(PersonRequest request) {
        return Person.builder()
                .name(request.getName())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

    }

    public static PersonResponse personEntityToResponse(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .telephone(person.getTelephone())
                .registrationDate(person.getRegistrationDate())
                .updateDate(person.getUpdateDate())
                .status(person.getStatus().name())
                .build();
    }

}
