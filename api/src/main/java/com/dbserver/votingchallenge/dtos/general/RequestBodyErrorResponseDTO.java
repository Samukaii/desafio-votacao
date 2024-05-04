package com.dbserver.votingchallenge.dtos.general;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class RequestBodyErrorResponseDTO {
    public List<RequestBodyErrorDetailDTO> errors;

    public RequestBodyErrorResponseDTO(MethodArgumentNotValidException exception) {
        this.errors = exception.getFieldErrors().stream().map(error ->
                new RequestBodyErrorDetailDTO(error.getField(), error.getDefaultMessage())
        ).toList();
    }
}
