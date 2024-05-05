package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.dtos.cpfvalidation.CpfValidationResultDTO;
import com.dbserver.votingchallenge.enums.CpfValidationStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/validate_cpf")
@Tag(name = "Validação de CPF")
@RequiredArgsConstructor
public class CpfValidationControllers {

    @Operation(summary = "Valida aleatóriamente um CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "CPF inválido"),
    })
    @GetMapping("{cpf}")
    public ResponseEntity<CpfValidationResultDTO> create(@PathVariable String cpf) {
        boolean randomResult = Math.random() < 0.5;

        CpfValidationStatus status = randomResult
                ? CpfValidationStatus.ABLE_TO_VOTE
                : CpfValidationStatus.UNABLE_TO_VOTE;

        CpfValidationResultDTO resultDTO = new CpfValidationResultDTO(status);

        if (randomResult)
            return ResponseEntity.ok(resultDTO);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultDTO);
    }
}
