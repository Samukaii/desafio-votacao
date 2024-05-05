package com.dbserver.votingchallenge.dtos.associated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(name = "Criação de associado")
public record AssociatedCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        @Digits(integer = 11, fraction = 0)
        String cpf
) {
}
