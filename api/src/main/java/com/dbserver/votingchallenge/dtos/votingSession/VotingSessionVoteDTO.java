package com.dbserver.votingchallenge.dtos.votingSession;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "Votar em uma pauta")
public record VotingSessionVoteDTO(
        @NotNull
        Boolean favorable,
        @NotNull
        String associatedCpf
) {
}
