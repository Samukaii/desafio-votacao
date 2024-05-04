package com.dbserver.votingchallenge.dtos.votingSession;

import jakarta.validation.constraints.NotNull;

public record VotingSessionVoteDTO(
        @NotNull
        Boolean favorable,
        @NotNull
        String associatedCpf
) {
}
