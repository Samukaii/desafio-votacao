package com.dbserver.votingchallenge.mappers.agenda;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AgendaMapperTest {
    @InjectMocks
    private AgendaMapper agendaMapper;

    @Mock
    private VoteService voteService;

    private final AgendaFaker agendaFaker = new AgendaFaker();

    @Test
    void shallConvertToDTO() {
        Agenda agenda = agendaFaker.createOne();

        AgendaResponseListDTO dto = agendaMapper.toDto(agenda);

        assertEquals(dto.id(), agenda.getId());
        assertEquals(dto.name(), agenda.getName());
        assertEquals(dto.description(), agenda.getDescription());
    }

    @Test
    void shallConvertToDTOList() {
        List<Agenda> agendas = agendaFaker.createList(1);

        List<AgendaResponseListDTO> dtoS = agendaMapper.toDtoS(agendas);

        assertEquals(dtoS.get(0).id(), agendas.get(0).getId());
        assertEquals(dtoS.get(0).name(), agendas.get(0).getName());
        assertEquals(dtoS.get(0).description(), agendas.get(0).getDescription());
    }
}
