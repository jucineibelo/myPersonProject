package com.stay_fine.exception;

import com.stay_fine.enums.Error;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final Integer code;

    public BadRequestException(Error error) {
        super(error.getMessage());
        this.code = error.getCode();
    }
}
