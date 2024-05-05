package com.dbserver.votingchallenge.dtos.associated;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Retorno de um associado")
public record AssociatedResponseDTO(
        Integer id,
        String name,
        String cpf
) {
}
