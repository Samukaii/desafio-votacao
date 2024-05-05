package com.dbserver.votingchallenge.dtos.vote;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Criar um voto")
public record VoteCreateDTO(
        Boolean favorable,
        Associated associated,
        VotingSession votingSession
) {
}
