package com.stay_fine.enums;

import lombok.Getter;

@Getter
public enum Error {

    NOT_FOUND_EXCEPTION(404, "Recurso não encontrado"),

    BAD_REQUEST_EXCEPTION(400, "Requisição Inválida");

    private final Integer code;

    private String message;

    Error(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
