package com.dbserver.votingchallenge.mappers.associated;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.dtos.associated.AssociatedCreateDTO;
import com.dbserver.votingchallenge.dtos.associated.AssociatedResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociatedMapper {
    public Associated toEntity(AssociatedCreateDTO dto) {
        return Associated.builder()
                .name(dto.name())
                .cpf(dto.cpf())
                .build();
    }

    public AssociatedResponseDTO toDto(Associated vote) {
        return AssociatedResponseDTO.builder()
                .id(vote.getId())
                .name(vote.getName())
                .cpf(vote.getCpf())
                .build();
    }

    public List<AssociatedResponseDTO> toDtoS(List<Associated> associates) {
        return associates.stream().map(this::toDto).toList();
    }
}
