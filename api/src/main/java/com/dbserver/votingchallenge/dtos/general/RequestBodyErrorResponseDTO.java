package com.dbserver.votingchallenge.dtos.general;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class RequestBodyErrorResponseDTO {
    public List<RequestBodyErrorDetailDTO> fieldErrors;

    public RequestBodyErrorResponseDTO(MethodArgumentNotValidException exception) {
        this.fieldErrors = exception.getFieldErrors().stream().map(error ->
                new RequestBodyErrorDetailDTO(error.getField(), error.getDefaultMessage())
        ).toList();
    }
}
