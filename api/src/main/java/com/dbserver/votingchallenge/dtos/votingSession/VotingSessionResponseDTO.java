package com.dbserver.votingchallenge.dtos.votingSession;

import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.dtos.general.StatusDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Retorno de uma sessão de votação")
public record VotingSessionResponseDTO(
        Integer id,
        AgendaResponseListDTO agenda,
        StatusDTO status
) {
}
