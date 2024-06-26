package com.dbserver.votingchallenge.domain.vote;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.associated.AssociatedService;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.dtos.agenda.AgendaVoteDTO;
import com.dbserver.votingchallenge.dtos.vote.VoteCreateDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.exceptions.vote.UserAlreadyVotedException;
import com.dbserver.votingchallenge.mappers.vote.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final AssociatedService associatedService;
    private final VoteRepository voteRepository;
    private final VoteMapper mapper;

    public Vote create(VotingSession votingSession, AgendaVoteDTO data) {
        Associated associated = associatedService.getOneByCpf(data.associatedCpf());

        validateUserAlreadyVoted(associated, votingSession);

        VoteCreateDTO createDTO = VoteCreateDTO.builder()
                .associated(associated)
                .favorable(data.favorable())
                .votingSession(votingSession)
                .build();

        Vote vote = mapper.toEntity(createDTO);

        voteRepository.save(vote);

        return vote;
    }

    public VotingSessionResultDTO getResult(VotingSession votingSession) {
        List<Vote> votesInFavor = voteRepository.findAllByFavorableTrueAndVotingSession(votingSession);
        List<Vote> votesNotInFavor = voteRepository.findAllByFavorableFalseAndVotingSession(votingSession);
        List<Vote> allVotes = voteRepository.findAllByVotingSession(votingSession);

        return new VotingSessionResultDTO(votesInFavor.size(), votesNotInFavor.size(), allVotes.size());
    }

    private void validateUserAlreadyVoted(Associated associated, VotingSession votingSession) {
        Optional<Vote> voteOptional = voteRepository.findByAssociatedAndVotingSession(associated, votingSession);

        if(voteOptional.isPresent())
            throw new UserAlreadyVotedException();
    }
}
