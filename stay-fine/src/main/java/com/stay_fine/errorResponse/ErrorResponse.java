package com.stay_fine.errorResponse;

import java.util.List;

public class ErrorResponse {

    Integer httCode;
    String message;
    List<FieldErrorResponse> erros;

    public ErrorResponse(Integer httCode, String message, List<FieldErrorResponse> erros) {
        this.httCode = httCode;
        this.message = message;
        this.erros = erros;
    }

    public Integer getHttCode() {
        return httCode;
    }

    public void setHttCode(Integer httCode) {
        this.httCode = httCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorResponse> getErros() {
        return erros;
    }

    public void setErros(List<FieldErrorResponse> erros) {
        this.erros = erros;
    }
}
