package com.dbserver.votingchallenge.dtos.agenda;

import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Retorno detalhado de uma pauta")
public record AgendaResponseSingleDTO(
        Integer id,
        String name,
        String description,
        VotingSessionResultDTO results
) {}
