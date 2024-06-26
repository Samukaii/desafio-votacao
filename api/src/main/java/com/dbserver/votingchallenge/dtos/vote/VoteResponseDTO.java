package com.dbserver.votingchallenge.dtos.vote;

import com.dbserver.votingchallenge.domain.associated.Associated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Retorno de um voto")
public record VoteResponseDTO(
        Integer id,
        Boolean favorable,
        Associated associated
) {
}
