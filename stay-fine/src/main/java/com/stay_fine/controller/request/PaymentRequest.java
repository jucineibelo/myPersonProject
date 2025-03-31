package com.stay_fine.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentRequest {

    @NotBlank(message = "O descrição nome deve ser preenchido")
    private String description;

    @NotBlank(message = "O campo tipo deve ser preenchido")
    private String tipo;
}
