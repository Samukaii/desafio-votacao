package com.dbserver.votingchallenge.dtos.votingSession;

import lombok.Builder;

@Builder
public record VotingSessionResultDTO(
        long votesInFavor,
        long votesNotInFavor,
        long totalVotes
) {}
