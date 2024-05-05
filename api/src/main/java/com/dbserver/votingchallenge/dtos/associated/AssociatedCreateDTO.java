package com.dbserver.votingchallenge.dtos.associated;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AssociatedCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        String cpf
) {
}
