package com.dbserver.votingchallenge.dtos.associated;

import lombok.Builder;

@Builder
public record AssociatedResponseDTO(
        Integer id,
        String name,
        String cpf
) {
}
