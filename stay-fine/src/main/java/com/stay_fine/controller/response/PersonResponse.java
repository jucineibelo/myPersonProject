package com.stay_fine.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class PersonResponse {
    Long id;

    String name;

    String email;

    String telephone;

    LocalDateTime registrationDate;

    LocalDateTime updateDate;

    String status;

}
