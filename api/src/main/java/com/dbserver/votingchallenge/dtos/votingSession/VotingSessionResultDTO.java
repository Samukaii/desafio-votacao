package com.dbserver.votingchallenge.dtos.votingSession;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Resultado de uma votação")
public record VotingSessionResultDTO(
        long votesInFavor,
        long votesNotInFavor,
        long totalVotes
) {}
