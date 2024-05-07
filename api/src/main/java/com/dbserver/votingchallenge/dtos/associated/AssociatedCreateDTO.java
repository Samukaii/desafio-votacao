package com.dbserver.votingchallenge.dtos.associated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(name = "Criação de associado")
public record AssociatedCreateDTO(
        @NotBlank(message = "Não pode ficar em branco")
        String name,
        @NotBlank(message = "Não pode ficar em branco")
        @Digits(integer = 11, fraction = 2, message = "O CPF não pode ter mais de 11 dígitos")
        String cpf
) {
}
