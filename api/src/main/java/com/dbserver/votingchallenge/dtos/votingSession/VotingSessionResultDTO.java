package com.dbserver.votingchallenge.dtos.votingSession;

public record VotingSessionResultDTO(
        long votesInFavor,
        long votesNotInFavor,
        long totalVotes
) {}
