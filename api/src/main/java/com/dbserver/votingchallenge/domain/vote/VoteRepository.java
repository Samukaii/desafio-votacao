package com.dbserver.votingchallenge.domain.vote;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    List<Vote> findAllByFavorableTrueAndVotingSession(VotingSession votingSession);
    List<Vote> findAllByFavorableFalseAndVotingSession(VotingSession votingSession);
    List<Vote> findAllByVotingSession(VotingSession votingSession);
    Optional<Vote> findByAssociatedAndVotingSession(Associated associated, VotingSession votingSession);
}
