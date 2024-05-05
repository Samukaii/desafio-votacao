package com.dbserver.votingchallenge.config;

import com.dbserver.votingchallenge.dtos.general.ErrorResponseDTO;
import com.dbserver.votingchallenge.dtos.general.RequestBodyErrorResponseDTO;
import com.dbserver.votingchallenge.exceptions.general.ConflictException;
import com.dbserver.votingchallenge.exceptions.general.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyRegistered(ConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAgendaNotFound(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestBodyErrorResponseDTO> handleArgument(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new RequestBodyErrorResponseDTO(exception));
    }
}
