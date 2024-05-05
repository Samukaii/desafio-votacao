package com.dbserver.votingchallenge.mappers.vote;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.dtos.vote.VoteResponseDTO;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import com.dbserver.votingchallenge.fakers.associated.AssociatedFaker;
import com.dbserver.votingchallenge.fakers.vote.VoteFaker;
import com.dbserver.votingchallenge.fakers.votingSession.VotingSessionFaker;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class VoteMapperTest {
    @InjectMocks
    private VoteMapper voteMapper;

    private final AgendaFaker agendaFaker = new AgendaFaker();
    private final VotingSessionFaker votingSessionFaker = new VotingSessionFaker(agendaFaker);
    private final AssociatedFaker associatedFaker = new AssociatedFaker();
    private final VoteFaker voteFaker = new VoteFaker(votingSessionFaker, associatedFaker);

    @Test
    void shallConvertToDTO() {
        Vote vote = voteFaker.createOne();

        VoteResponseDTO dto = voteMapper.toDto(vote);

        assertEquals(dto.id(), vote.getId());
        assertEquals(dto.favorable(), vote.isFavorable());
        assertEquals(dto.associated(), vote.getAssociated());
    }
}
