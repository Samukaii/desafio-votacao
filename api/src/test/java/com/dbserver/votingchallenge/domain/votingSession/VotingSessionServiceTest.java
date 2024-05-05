package com.dbserver.votingchallenge.domain.votingSession;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.agenda.AgendaService;
import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import com.dbserver.votingchallenge.mappers.votingSession.VotingSessionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    VotingSessionMapper mapper;

    @Mock
    VoteService voteService;

    @Mock
    private ScheduledExecutorService scheduledExecutorService;

    private final AgendaFaker agendaFaker = new AgendaFaker();

    @BeforeEach
    void setUp() {
        this.votingSessionService.setScheduledExecutorService(scheduledExecutorService);
    }

    @Test
    void shallCreateVotingSessionAndCloseAfterDelimitedTime() {
        Agenda expectedAgenda = agendaFaker.createOne();

        VotingSession expectedVotingSession = new VotingSession();
        expectedVotingSession.setAgenda(expectedAgenda);
        expectedVotingSession.setStatus(VotingSessionStatus.OPENED);

        long expectedMinutes = Duration.ofMinutes(5).toMinutes();

        doReturn(null).when(scheduledExecutorService).schedule(
                any(Runnable.class),
                eq(expectedMinutes),
                eq(TimeUnit.MINUTES)
        );

        when(mapper.toEntity(any(Agenda.class))).thenReturn(expectedVotingSession);

        VotingSession result = votingSessionService.create(expectedAgenda, 5);

        assertEquals(expectedVotingSession, result);

        VotingSession expectedVotingSessionAfterClosed = new VotingSession();
        expectedVotingSession.setAgenda(expectedAgenda);
        expectedVotingSession.setStatus(VotingSessionStatus.ACCEPTED);

        verify(votingSessionRepository).save(expectedVotingSession);

        verify(scheduledExecutorService).schedule(
                any(Runnable.class),
                eq(expectedMinutes),
                eq(TimeUnit.MINUTES)
        );

        await().atMost(expectedMinutes, TimeUnit.MINUTES)
                .untilAsserted(() ->
                        verify(
                                votingSessionRepository
                        ).save(expectedVotingSessionAfterClosed));
    }
}
