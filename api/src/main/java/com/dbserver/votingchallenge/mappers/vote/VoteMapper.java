package com.dbserver.votingchallenge.mappers.vote;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.dtos.vote.VoteResponseDTO;

public class VoteMapper {
    public static VoteResponseDTO toDto(Vote vote) {
        return VoteResponseDTO.builder()
                .id(vote.getId())
                .favorable(vote.isFavorable())
                .associated(vote.getAssociated())
                .build();
    }
}
