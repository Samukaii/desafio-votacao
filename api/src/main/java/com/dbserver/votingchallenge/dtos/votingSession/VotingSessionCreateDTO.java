package com.dbserver.votingchallenge.dtos.votingSession;

import jakarta.validation.constraints.NotNull;

public record VotingSessionCreateDTO(
        @NotNull
        Integer agendaId,
        Integer timeInMinutes
) {
}
