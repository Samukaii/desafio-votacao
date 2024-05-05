package com.dbserver.votingchallenge.dtos.cpfvalidation;

import com.dbserver.votingchallenge.enums.CpfValidationStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Retorno da validação de CPF")
public record CpfValidationResultDTO(
        CpfValidationStatus status
) {
}
