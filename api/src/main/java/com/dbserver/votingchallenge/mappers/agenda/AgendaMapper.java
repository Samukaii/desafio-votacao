package com.dbserver.votingchallenge.mappers.agenda;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseSingleDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;

import java.util.List;

public class AgendaMapper {
    public static AgendaResponseListDTO toDto(Agenda agenda) {
        return new AgendaResponseListDTO(
                agenda.getId(),
                agenda.getName(),
                agenda.getDescription()
        );
    }

    public static List<AgendaResponseListDTO> toDtoS(List<Agenda> agendas) {
        return agendas.stream().map(AgendaMapper::toDto).toList();
    }

    public static AgendaResponseSingleDTO toSingleDto(Agenda agenda, VotingSessionResultDTO result) {
        return new AgendaResponseSingleDTO(
                agenda.getId(),
                agenda.getName(),
                agenda.getDescription(),
                result
        );
    }
}
