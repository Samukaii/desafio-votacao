package com.dbserver.votingchallenge.dtos.general;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Situação")
public record StatusDTO(
        Integer id,
        String name
) {
}
