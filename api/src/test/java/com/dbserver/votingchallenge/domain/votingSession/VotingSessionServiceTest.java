package com.dbserver.votingchallenge.domain.votingSession;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.agenda.AgendaService;
import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionCreateDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionVoteDTO;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.exceptions.votingSessions.VotingSessionNotFoundException;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import com.dbserver.votingchallenge.fakers.votingSession.VotingSessionFaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VotingSessionServiceTest {
    @InjectMocks
    VotingSessionService votingSessionService;

    @Mock
    AgendaService agendaService;

    @Mock
    VotingSessionRepository votingSessionRepository;

    @Mock
    VoteService voteService;

    @Mock
    private ScheduledExecutorService scheduledExecutorService;

    @BeforeEach
    void setUp() {
        this.votingSessionService.setScheduledExecutorService(scheduledExecutorService);
    }

    @Test
    void shallCreateVotingSessionAndCloseAfterDelimitedTime() {
        Integer agendaId = 1;
        Agenda expectedAgenda = AgendaFaker.createOne();

        VotingSession expectedVotingSession = new VotingSession();
        expectedVotingSession.setAgenda(expectedAgenda);
        expectedVotingSession.setStatus(VotingSessionStatus.OPENED);

        VotingSessionCreateDTO dto = new VotingSessionCreateDTO(
                agendaId,
                5
        );

        long expectedMinutes = Duration.ofMinutes(dto.timeInMinutes()).toMinutes();

        when(agendaService.getOne(agendaId)).thenReturn(expectedAgenda);

        doReturn(null).when(scheduledExecutorService).schedule(
                any(Runnable.class),
                eq(expectedMinutes),
                eq(TimeUnit.MINUTES)
        );

        VotingSession result = votingSessionService.create(dto);

        assertEquals(expectedVotingSession, result);

        VotingSession expectedVotingSessionAfterClosed = new VotingSession();
        expectedVotingSession.setAgenda(expectedAgenda);
        expectedVotingSession.setStatus(VotingSessionStatus.CLOSED);

        verify(votingSessionRepository).save(expectedVotingSession);

        verify(scheduledExecutorService).schedule(
                any(Runnable.class),
                eq(expectedMinutes),
                eq(TimeUnit.MINUTES)
        );

        await().atMost(dto.timeInMinutes(), TimeUnit.MINUTES)
                .untilAsserted(() ->
                        verify(
                                votingSessionRepository
                        ).save(expectedVotingSessionAfterClosed));
    }

    @Test
    void shallCreateVotingSessionAndCloseAfterDefaultTime() {
        Integer agendaId = 1;
        Agenda expectedAgenda = AgendaFaker.createOne();

        VotingSession expectedVotingSessionAfterClosed = new VotingSession();
        expectedVotingSessionAfterClosed.setAgenda(expectedAgenda);
        expectedVotingSessionAfterClosed.setStatus(VotingSessionStatus.CLOSED);

        VotingSessionCreateDTO dto = new VotingSessionCreateDTO(
                agendaId,
                null
        );

        long expectedMinutes = Duration.ofMinutes(1).toMinutes();

        when(agendaService.getOne(agendaId)).thenReturn(expectedAgenda);

        doReturn(null).when(scheduledExecutorService).schedule(
                any(Runnable.class),
                eq(expectedMinutes),
                eq(TimeUnit.MINUTES)
        );

        votingSessionService.create(dto);

        verify(scheduledExecutorService).schedule(
                any(Runnable.class),
                eq(expectedMinutes),
                eq(TimeUnit.MINUTES)
        );

        await().atMost(1, TimeUnit.MINUTES)
                .untilAsserted(() ->
                        verify(
                                votingSessionRepository
                        ).save(expectedVotingSessionAfterClosed));
    }

    @Test
    void shallVote() {
        Integer votingSessionId = 1;
        VotingSession expectedVotingSession = VotingSessionFaker.createOne();

        VotingSessionVoteDTO dto = new VotingSessionVoteDTO(
                true,
                "024.966.920-05"
        );

        when(votingSessionRepository.findById(votingSessionId))
                .thenReturn(Optional.of(expectedVotingSession));

        votingSessionService.vote(votingSessionId, dto);

        verify(voteService).create(expectedVotingSession, dto);
        verifyNoMoreInteractions(voteService);
    }


    @Test
    void shallGetAllVotingSessions() {
        List<VotingSession> expectedVotingSessions = VotingSessionFaker.createList(5);

        when(votingSessionRepository.findAll()).thenReturn(expectedVotingSessions);

        List<VotingSession> result = votingSessionService.getAll();

        assertEquals(result, expectedVotingSessions);

        verify(votingSessionRepository).findAll();
        verifyNoMoreInteractions(votingSessionRepository);
    }

    @Test
    void shallGetOneVotingSession() {
        Integer votingSessionId = 1;

        VotingSession expectedVotingSession = new VotingSession();
        expectedVotingSession.setId(votingSessionId);

        when(votingSessionRepository.findById(votingSessionId))
                .thenReturn(Optional.of(expectedVotingSession));

        VotingSession result = votingSessionService.getOne(votingSessionId);

        assertEquals(result, expectedVotingSession);

        verify(votingSessionRepository).findById(votingSessionId);
        verifyNoMoreInteractions(votingSessionRepository);
    }

    @Test
    void shallThrowErrorWhenFindNoVotingSession() {
        Integer votingSessionId = 1;

        when(votingSessionRepository.findById(votingSessionId)).thenReturn(Optional.empty());

        final VotingSessionNotFoundException e = assertThrows(VotingSessionNotFoundException.class, () ->
                votingSessionService.getOne(votingSessionId)
        );

        assertEquals("Voting session not found", e.getMessage());

        verify(votingSessionRepository).findById(votingSessionId);
        verifyNoMoreInteractions(votingSessionRepository);
    }
}
