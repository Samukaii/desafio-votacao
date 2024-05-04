package com.dbserver.votingchallenge.dtos.vote;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResponseDTO;

public record VoteResponseDTO(
        Integer id,
        Boolean favorable,
        Associated associated,
        VotingSessionResponseDTO votingSession
) {
}
