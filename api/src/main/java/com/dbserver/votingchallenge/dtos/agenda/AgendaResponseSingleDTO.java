package com.dbserver.votingchallenge.dtos.agenda;

import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;

public record AgendaResponseSingleDTO(
        Integer id,
        String name,
        String description,
        VotingSessionResultDTO results
) {}
