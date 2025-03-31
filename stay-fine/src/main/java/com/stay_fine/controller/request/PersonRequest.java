package com.stay_fine.controller.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {

    @NotBlank(message = "O campo nome deve ser preenchido")
    String name;

    @Email(message = "O e-mail deve ser valido")
    @NotBlank(message = "O campo e-mail deve ser preenchido")
    String email;

    String telephone;

    @NotBlank(message = "O campo username deve ser preenchido")
    String username;

    @NotBlank(message = "O campo senha deve ser preenchido")
    String password;
}
