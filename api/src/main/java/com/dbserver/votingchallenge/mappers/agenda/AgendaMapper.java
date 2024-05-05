package com.dbserver.votingchallenge.mappers.agenda;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseSingleDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResultDTO;

import java.util.List;

public class AgendaMapper {
    public static AgendaResponseListDTO toDto(Agenda agenda) {
        return AgendaResponseListDTO.builder()
                .id(agenda.getId())
                .name(agenda.getName())
                .description(agenda.getDescription())
                .build();
    }

    public static List<AgendaResponseListDTO> toDtoS(List<Agenda> agendas) {
        return agendas.stream().map(AgendaMapper::toDto).toList();
    }

    public static AgendaResponseSingleDTO toSingleDto(Agenda agenda, VotingSessionResultDTO results) {
        return AgendaResponseSingleDTO.builder()
                .id(agenda.getId())
                .name(agenda.getName())
                .description(agenda.getDescription())
                .results(results)
                .build();
    }
}
