package com.dbserver.votingchallenge.dtos.agenda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Abrir uma sessão de votação")
public record AgendaOpenVotingSessionDTO(
        Integer timeInMinutes
) {
}
