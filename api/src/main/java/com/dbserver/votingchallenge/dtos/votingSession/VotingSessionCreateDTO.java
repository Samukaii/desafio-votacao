package com.dbserver.votingchallenge.dtos.votingSession;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "Abrir uma sessão de votação")
public record VotingSessionCreateDTO(
        @NotNull
        Integer agendaId,
        Integer timeInMinutes
) {
}
