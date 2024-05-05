package com.dbserver.votingchallenge.mappers.vote;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.dtos.vote.VoteResponseDTO;
import com.dbserver.votingchallenge.mappers.votingSession.VotingSessionMapper;

public class VoteMapper {
    public static VoteResponseDTO toDto(Vote vote) {
        return VoteResponseDTO.builder()
                .id(vote.getId())
                .favorable(vote.isFavorable())
                .associated(vote.getAssociated())
                .votingSession(VotingSessionMapper.toDto(vote.getVotingSession()))
                .build();
    }
}
