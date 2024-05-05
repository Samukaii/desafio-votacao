package com.dbserver.votingchallenge.dtos.votingSession;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record VotingSessionCreateDTO(
        @NotNull
        Integer agendaId,
        Integer timeInMinutes
) {
}
