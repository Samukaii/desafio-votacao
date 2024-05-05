package com.dbserver.votingchallenge.dtos.agenda;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AgendaCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        String description
) {}