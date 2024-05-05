package com.dbserver.votingchallenge.dtos.agenda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Retorno resumido de uma pauta")
public record AgendaResponseListDTO(
        Integer id,
        String name,
        String description
) {
}
