package com.dbserver.votingchallenge.domain.votingSession;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.exceptions.agenda.AgendaAlreadyOpenVotingSessionException;
import com.dbserver.votingchallenge.mappers.votingSession.VotingSessionMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class VotingSessionService {
    private final VotingSessionRepository votingSessionRepository;
    private final VotingSessionMapper mapper;
    private final VoteService voteService;

    @Setter
    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public VotingSession create(Agenda agenda) {
        validateAgendaIsOpened(agenda);

        VotingSession votingSession = mapper.toEntity(agenda);

        scheduleClosing(votingSession, 1);

        return votingSession;
    }

    public VotingSession create(Agenda agenda, Integer timeInMinutes) {
        validateAgendaIsOpened(agenda);

        VotingSession votingSession = mapper.toEntity(agenda);

        scheduleClosing(votingSession, Objects.requireNonNullElse(timeInMinutes, 1));

        votingSessionRepository.save(votingSession);

        return votingSession;
    }

    private void scheduleClosing(VotingSession votingSession, Integer minutes) {
        scheduledExecutorService.schedule(() ->
                        closeVotingSession(votingSession),
                Duration.ofMinutes(minutes).toMinutes(),
                TimeUnit.MINUTES
        );
    }

    private void closeVotingSession(VotingSession votingSession) {
        VotingSessionResultDTO result = voteService.getResult(votingSession);

        VotingSessionStatus status = result.votesInFavor() > result.votesNotInFavor()
                ? VotingSessionStatus.ACCEPTED
                : result.votesInFavor() < result.votesNotInFavor()
                ? VotingSessionStatus.REJECTED
                : VotingSessionStatus.DRAW;

        votingSession.setStatus(status);
        votingSessionRepository.save(votingSession);
    }

    private void validateAgendaIsOpened(Agenda agenda) {
        Optional<VotingSession> votingSessionOptional = votingSessionRepository.findByAgenda(agenda);

        if (votingSessionOptional.isPresent())
            throw new AgendaAlreadyOpenVotingSessionException();
    }
}
