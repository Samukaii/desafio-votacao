package com.dbserver.votingchallenge.dtos.associated;

import jakarta.validation.constraints.NotBlank;

public record AssociatedCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        String cpf
) {
}
