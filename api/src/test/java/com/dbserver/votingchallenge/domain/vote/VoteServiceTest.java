package com.dbserver.votingchallenge.domain.vote;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.associated.AssociatedService;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionVoteDTO;
import com.dbserver.votingchallenge.exceptions.vote.UserAlreadyVotedException;
import com.dbserver.votingchallenge.fakers.associated.AssociatedFaker;
import com.dbserver.votingchallenge.fakers.vote.VoteFaker;
import com.dbserver.votingchallenge.fakers.votingSession.VotingSessionFaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
    @InjectMocks
    VoteService voteService;

    @Mock
    VoteRepository voteRepository;

    @Mock
    AssociatedService associatedService;

    @Test
    void shallCreateVoteSuccessfully() {
        VotingSessionVoteDTO dto = new VotingSessionVoteDTO(
                true,
                "8749847234"
        );

        Associated expectedAssociated = AssociatedFaker.createOne();
        VotingSession votingSession = VotingSessionFaker.createOne();

        Vote expectedVote = new Vote();

        expectedVote.setAssociated(expectedAssociated);
        expectedVote.setVotingSession(votingSession);
        expectedVote.setFavorable(dto.favorable());

        when(voteRepository.save(any(Vote.class)))
                .thenReturn(expectedVote);

        when(associatedService.getOneByCpf(dto.associatedCpf()))
                .thenReturn(expectedAssociated);

        Vote result = voteService.create(votingSession, dto);

        assertEquals(result, expectedVote);

        verify(associatedService).getOneByCpf(dto.associatedCpf());
        verifyNoMoreInteractions(associatedService);

        verify(voteRepository)
                .findByAssociatedAndVotingSession(expectedAssociated, votingSession);
        verify(voteRepository).save(any(Vote.class));
        verifyNoMoreInteractions(voteRepository);
    }

    @Test
    void shallThrowErrorWhenAssociatedAlreadyVoted() {
        VotingSessionVoteDTO dto = new VotingSessionVoteDTO(
                true,
                "8749847234"
        );

        VotingSession votingSession = VotingSessionFaker.createOne();
        Associated expectedAssociated = AssociatedFaker.createOne();

        Vote expectedVote = new Vote();

        expectedVote.setAssociated(expectedAssociated);
        expectedVote.setVotingSession(votingSession);
        expectedVote.setFavorable(dto.favorable());


        when(associatedService.getOneByCpf(dto.associatedCpf())).thenReturn(expectedAssociated);

        when(voteRepository.findByAssociatedAndVotingSession(expectedAssociated, votingSession))
                .thenReturn(Optional.of(VoteFaker.createOne()));

        UserAlreadyVotedException exception = assertThrows(UserAlreadyVotedException.class, () ->
                voteService.create(votingSession, dto)
        );

        assertEquals(exception.getMessage(), "Este usuário já votou nesta pauta");

        verify(associatedService).getOneByCpf(dto.associatedCpf());
        verifyNoMoreInteractions(associatedService);

        verify(voteRepository).findByAssociatedAndVotingSession(expectedAssociated, votingSession);
        verifyNoMoreInteractions(voteRepository);
    }

    @Test
    void shallGetResultByVotingSession() {
        VotingSession votingSession = VotingSessionFaker.createOne();
        Integer votesInFavor = 5;
        Integer votesNotInFavor = 3;
        Integer totalVotes = 8;


        when(voteRepository.findAllByFavorableTrueAndVotingSession(votingSession))
                .thenReturn(VoteFaker.createList(votesInFavor));

        when(voteRepository.findAllByFavorableFalseAndVotingSession(votingSession))
                .thenReturn(VoteFaker.createList(votesNotInFavor));

        when(voteRepository.findAllByVotingSession(votingSession))
                .thenReturn(VoteFaker.createList(totalVotes));

        VotingSessionResultDTO result = voteService.getResult(votingSession);


        assertEquals(result.votesInFavor(), 5);
        assertEquals(result.votesNotInFavor(), 3);
        assertEquals(result.totalVotes(), 8);

        verify(voteRepository).findAllByFavorableTrueAndVotingSession(votingSession);
        verify(voteRepository).findAllByFavorableFalseAndVotingSession(votingSession);
        verify(voteRepository).findAllByVotingSession(votingSession);

        verifyNoMoreInteractions(voteRepository);
    }
}
