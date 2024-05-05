package com.dbserver.votingchallenge.dtos.agenda;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(name = "Criação de pauta")
public record AgendaCreateDTO(
        @NotBlank(message = "Não pode ficar em branco")
        String name,
        @NotBlank(message = "Não pode ficar em branco")
        String description
) {}