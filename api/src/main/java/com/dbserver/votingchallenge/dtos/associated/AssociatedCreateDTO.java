package com.dbserver.votingchallenge.dtos.associated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(name = "Criação de associado")
public record AssociatedCreateDTO(
        @NotBlank(message = "Não pode ficar em branco")
        String name,
        @NotBlank(message = "Não pode ficar em branco")
        String cpf
) {
}
