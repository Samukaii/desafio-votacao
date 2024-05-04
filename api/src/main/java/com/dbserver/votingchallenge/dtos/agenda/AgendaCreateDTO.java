package com.dbserver.votingchallenge.dtos.agenda;
import jakarta.validation.constraints.NotBlank;

public record AgendaCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        String description
) {}
