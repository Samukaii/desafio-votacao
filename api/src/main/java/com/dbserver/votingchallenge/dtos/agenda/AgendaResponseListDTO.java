package com.dbserver.votingchallenge.dtos.agenda;

import com.dbserver.votingchallenge.dtos.general.StatusDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Retorno resumido de uma pauta")
public record AgendaResponseListDTO(
        Integer id,
        String name,
        String description,
        StatusDTO status,
        VotingSessionResultDTO results
) {
}
