package com.dbserver.votingchallenge.mappers.vote;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.dtos.general.StatusDTO;
import com.dbserver.votingchallenge.dtos.vote.VoteResponseDTO;
import com.dbserver.votingchallenge.mappers.votingSession.VotingSessionMapper;

public class VoteMapper {
    public static VoteResponseDTO toDTO(Vote vote) {
        return new VoteResponseDTO(
                vote.getId(),
                vote.isFavorable(),
                vote.getAssociated(),
                VotingSessionMapper.toDto(vote.getVotingSession())
        );
    }
}
