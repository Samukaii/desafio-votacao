package com.dbserver.votingchallenge.config;

import com.dbserver.votingchallenge.dtos.general.ErrorResponseDTO;
import com.dbserver.votingchallenge.dtos.general.RequestBodyErrorResponseDTO;
import com.dbserver.votingchallenge.exceptions.agenda.AgendaNotFoundException;
import com.dbserver.votingchallenge.exceptions.associated.AssociatedCpfAlreadyUsedException;
import com.dbserver.votingchallenge.exceptions.associated.AssociatedNotFoundException;
import com.dbserver.votingchallenge.exceptions.vote.UserAlreadyVotedException;
import com.dbserver.votingchallenge.exceptions.votingSessions.VotingSessionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(UserAlreadyVotedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyRegistered(UserAlreadyVotedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(AssociatedCpfAlreadyUsedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyRegistered(AssociatedCpfAlreadyUsedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(AgendaNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAgendaNotFound(AgendaNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }
    @ExceptionHandler(VotingSessionNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleVotingSessionNotFound(VotingSessionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(AssociatedNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAssociatedNotFound(AssociatedNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestBodyErrorResponseDTO> handleArgument(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestBodyErrorResponseDTO(exception));
    }
}
