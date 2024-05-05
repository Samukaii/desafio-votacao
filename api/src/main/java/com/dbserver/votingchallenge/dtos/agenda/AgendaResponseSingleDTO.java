package com.dbserver.votingchallenge.dtos.agenda;

import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import lombok.Builder;

@Builder
public record AgendaResponseSingleDTO(
        Integer id,
        String name,
        String description,
        VotingSessionResultDTO results
) {}
