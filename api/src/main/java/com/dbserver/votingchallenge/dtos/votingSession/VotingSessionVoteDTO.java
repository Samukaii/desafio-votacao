package com.dbserver.votingchallenge.dtos.votingSession;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record VotingSessionVoteDTO(
        @NotNull
        Boolean favorable,
        @NotNull
        String associatedCpf
) {
}
