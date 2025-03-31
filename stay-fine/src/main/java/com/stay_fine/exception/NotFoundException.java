package com.stay_fine.exception;

import com.stay_fine.enums.Error;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final Integer code;

    public NotFoundException(Error error, Long id) {
        super(String.format("%s (ID: %d)", error.getMessage(), id));
        this.code = error.getCode();
    }
}
