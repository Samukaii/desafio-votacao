package com.dbserver.votingchallenge.dtos.agenda;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "Votar em uma pauta")
public record AgendaVoteDTO(
        @NotNull(message = "É obrigatório")
        Boolean favorable,
        @NotBlank(message = "Não pode ficar em branco")
        String associatedCpf
) {
}
