package com.dbserver.votingchallenge.controllers;

import com.dbserver.votingchallenge.dtos.cpfvalidation.CpfValidationResultDTO;
import com.dbserver.votingchallenge.enums.CpfValidationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/validate_cpf")
@RequiredArgsConstructor
public class CpfValidationControllers {
    @GetMapping("{cpf}")
    public ResponseEntity<CpfValidationResultDTO> create(@PathVariable String cpf) {
        boolean randomResult = Math.random() < 0.5;

        String status = randomResult
                ? CpfValidationStatus.ABLE_TO_VOTE.toString()
                : CpfValidationStatus.UNABLE_TO_VOTE.toString();

        CpfValidationResultDTO resultDTO = new CpfValidationResultDTO(status);

        if (randomResult)
            return ResponseEntity.ok(resultDTO);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultDTO);
    }
}
