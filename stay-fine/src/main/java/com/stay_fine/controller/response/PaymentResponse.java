package com.stay_fine.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PaymentResponse {

    private Long id;

    private String description;

    private String tipo;

    private LocalDateTime registrationDate;

    private LocalDateTime updateDate;

    private String status;

}
