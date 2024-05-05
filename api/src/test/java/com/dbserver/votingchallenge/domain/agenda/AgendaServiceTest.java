package com.dbserver.votingchallenge.domain.agenda;

import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.dtos.agenda.AgendaCreateDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.exceptions.agenda.AgendaNotFoundException;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceTest {
    @InjectMocks
    AgendaService agendaService;

    @Mock
    AgendaRepository agendaRepository;

    @Mock
    VoteService voteService;

    @Test
    void shallCreateAgenda() {
        AgendaCreateDTO dto = new AgendaCreateDTO(
                "Some agenda name",
                "Some agenda description"
        );

        Agenda expectedAgenda = new Agenda();
        expectedAgenda.setName(dto.name());
        expectedAgenda.setDescription(dto.description());

        when(agendaRepository.save(any(Agenda.class))).thenReturn(expectedAgenda);

        Agenda result = agendaService.create(dto);

        assertEquals(result.getName(), expectedAgenda.getName());
        assertEquals(result.getDescription(), expectedAgenda.getDescription());

        verify(agendaRepository).save(any(Agenda.class));
        verifyNoMoreInteractions(agendaRepository);
    }

    @Test
    void shallGetAllAgendas() {
        List<Agenda> expectedAgendas = AgendaFaker.createList(5);

        when(agendaRepository.findAll()).thenReturn(expectedAgendas);

        List<Agenda> result = agendaService.getAll();

        assertEquals(result, expectedAgendas);

        verify(agendaRepository).findAll();
        verifyNoMoreInteractions(agendaRepository);
    }

    @Test
    void shallGetOneAgenda() {
        Integer agendaId = 1;

        Agenda expectedAgenda = new Agenda();
        expectedAgenda.setId(agendaId);

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(expectedAgenda));

        Agenda result = agendaService.getOne(agendaId);

        assertEquals(result, expectedAgenda);

        verify(agendaRepository).findById(agendaId);
        verifyNoMoreInteractions(agendaRepository);
    }

    @Test
    void shallThrowErrorWhenFindNoAgenda() {
        Integer agendaId = 1;

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.empty());

        final AgendaNotFoundException e = assertThrows(AgendaNotFoundException.class, () ->
                agendaService.getOne(agendaId)
        );

        assertEquals("Pauta não encontrada", e.getMessage());

        verify(agendaRepository).findById(agendaId);
        verifyNoMoreInteractions(agendaRepository);
    }

    @Test
    void shallGetAgendaResult() {
        Integer agendaId = 1;

        Agenda agenda = new Agenda();
        VotingSession votingSession = new VotingSession();

        agenda.setId(agendaId);
        agenda.setVotingSession(votingSession);

        VotingSessionResultDTO expectedResult = new VotingSessionResultDTO(3, 4, 7);

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agenda));
        when(voteService.getResult(votingSession)).thenReturn(expectedResult);

        VotingSessionResultDTO result = agendaService.getResult(agendaId);

        assertEquals(result, expectedResult);

        verify(agendaRepository).findById(agendaId);
        verifyNoMoreInteractions(agendaRepository);
    }
}
