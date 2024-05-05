package com.dbserver.votingchallenge.domain.votingSession;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Integer> {
    Optional<VotingSession> findByAgenda(Agenda agenda);
}
