package com.dbserver.votingchallenge.domain.vote;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.voting.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    public List<Vote> findAllByFavorableTrueAndVotingSession(VotingSession votingSession);
    public List<Vote> findAllByFavorableFalseAndVotingSession(VotingSession votingSession);
    public List<Vote> findAllByVotingSession(VotingSession votingSession);
    public Optional<Vote> findByAssociatedAndVotingSession(Associated associated, VotingSession votingSession);
}
