package com.dbserver.votingchallenge.domain.voting;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.agenda.AgendaService;
import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionCreateDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResponseDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionVoteDTO;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.exceptions.votingSessions.VotingSessionNotFoundException;
import com.dbserver.votingchallenge.mappers.votingSession.VotingSessionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class VotingSessionService {
    private final AgendaService agendaService;
    private final VotingSessionRepository votingSessionRepository;
    private final VoteService voteService;
    private final TaskScheduler scheduler;

    public VotingSession create(VotingSessionCreateDTO data) {
        Agenda agenda = agendaService.getOne(data.agendaId());

        VotingSession votingSession = new VotingSession();
        votingSession.setAgenda(agenda);
        votingSession.setStatus(VotingSessionStatus.OPENED);
        votingSessionRepository.save(votingSession);

        if(data.timeInMinutes() == null)
            scheduleClosing(votingSession, 1);
        else scheduleClosing(votingSession, data.timeInMinutes());

        return votingSession;
    }

    public Vote vote(Integer votingSessionId, VotingSessionVoteDTO data) {
        VotingSession votingSession = getOne(votingSessionId);

        return voteService.create(votingSession, data);
    }

    public List<VotingSession> getAll() {
        return votingSessionRepository.findAll();
    }

    public VotingSession getOne(Integer votingSessionId) {
        return votingSessionRepository.findById(votingSessionId)
                .orElseThrow(VotingSessionNotFoundException::new);
    }

    private void scheduleClosing(VotingSession votingSession, Integer minutes) {
        scheduler.schedule(
                () -> closeVotingSession(votingSession),
                Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(minutes))
        );
    }

    private void closeVotingSession(VotingSession votingSession) {
        votingSession.setStatus(VotingSessionStatus.CLOSED);
        votingSessionRepository.save(votingSession);
    }
}
