package com.dbserver.votingchallenge.dtos.agenda;
import jakarta.validation.constraints.NotBlank;

public record AgendaResponseDTO(
        @NotBlank
        String name,
        @NotBlank
        String description
) {}
