package com.dbserver.votingchallenge.domain.voting;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Integer> {
}
