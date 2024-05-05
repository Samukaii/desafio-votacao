package com.dbserver.votingchallenge.mappers.vote;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.dtos.vote.VoteResponseDTO;
import com.dbserver.votingchallenge.fakers.vote.VoteFaker;
import com.dbserver.votingchallenge.mappers.votingSession.VotingSessionMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VoteMapperTest {
    @Test
    void shallConvertToDTO() {
        Vote vote = VoteFaker.createOne();

        VoteResponseDTO dto = VoteMapper.toDto(vote);

        assertEquals(dto.id(), vote.getId());
        assertEquals(dto.favorable(), vote.isFavorable());
        assertEquals(dto.associated(), vote.getAssociated());
        assertEquals(
                VotingSessionMapper.toDto(vote.getVotingSession()),
                dto.votingSession()
        );
    }
}
