package com.dbserver.votingchallenge.dtos.associated;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AssociatedCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        @Digits(integer = 11, fraction = 0)
        String cpf
) {
}
