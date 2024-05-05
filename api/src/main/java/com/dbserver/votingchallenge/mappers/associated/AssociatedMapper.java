package com.dbserver.votingchallenge.mappers.associated;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.dtos.associated.AssociatedResponseDTO;

import java.util.List;

public class AssociatedMapper {
    public static AssociatedResponseDTO toDto(Associated vote) {
        return AssociatedResponseDTO.builder()
                .id(vote.getId())
                .name(vote.getName())
                .cpf(vote.getCpf())
                .build();
    }

    public static List<AssociatedResponseDTO> toDtoS(List<Associated> associates) {
        return associates.stream().map(AssociatedMapper::toDto).toList();
    }
}
