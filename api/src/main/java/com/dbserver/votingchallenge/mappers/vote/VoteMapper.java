package com.dbserver.votingchallenge.mappers.vote;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.dtos.vote.VoteCreateDTO;
import com.dbserver.votingchallenge.dtos.vote.VoteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteMapper {
    public Vote toEntity(VoteCreateDTO dto) {
        return Vote.builder()
                .favorable(dto.favorable())
                .associated(dto.associated())
                .votingSession(dto.votingSession())
                .build();
    }

    public VoteResponseDTO toDto(Vote vote) {
        return VoteResponseDTO.builder()
                .id(vote.getId())
                .favorable(vote.isFavorable())
                .associated(vote.getAssociated())
                .build();
    }
}
