package com.dbserver.votingchallenge.dtos.agenda;

import lombok.Builder;

@Builder
public record AgendaResponseListDTO(
        Integer id,
        String name,
        String description
) {
}
