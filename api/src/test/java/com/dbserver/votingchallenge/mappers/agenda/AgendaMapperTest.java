package com.dbserver.votingchallenge.mappers.agenda;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseSingleDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AgendaMapperTest {
    @Test
    void shallConvertToDTO() {
        Agenda agenda = AgendaFaker.createOne();

        AgendaResponseListDTO dto = AgendaMapper.toDto(agenda);

        assertEquals(dto.id(), agenda.getId());
        assertEquals(dto.name(), agenda.getName());
        assertEquals(dto.description(), agenda.getDescription());
    }

    @Test
    void shallConvertToDTOList() {
        List<Agenda> agendas = AgendaFaker.createList(1);

        List<AgendaResponseListDTO> dtoS = AgendaMapper.toDtoS(agendas);

        assertEquals(dtoS.get(0).id(), agendas.get(0).getId());
        assertEquals(dtoS.get(0).name(), agendas.get(0).getName());
        assertEquals(dtoS.get(0).description(), agendas.get(0).getDescription());
    }

    @Test
    void shallConvertToSingleDTO() {
        Agenda agenda = AgendaFaker.createOne();
        VotingSessionResultDTO result = new VotingSessionResultDTO(
                1,
                2,
                3
        );

        AgendaResponseSingleDTO dto = AgendaMapper.toSingleDto(agenda, result);

        assertEquals(dto.id(), agenda.getId());
        assertEquals(agenda.getName(), dto.name());
        assertEquals(dto.description(), agenda.getDescription());
        assertEquals(dto.results(), result);
    }
}
