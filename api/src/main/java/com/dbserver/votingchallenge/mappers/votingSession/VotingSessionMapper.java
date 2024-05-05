package com.dbserver.votingchallenge.mappers.votingSession;

import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.dtos.general.StatusDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResponseDTO;
import com.dbserver.votingchallenge.mappers.agenda.AgendaMapper;

import java.util.List;

public class VotingSessionMapper {
    public static VotingSessionResponseDTO toDto(VotingSession votingSession) {
        return VotingSessionResponseDTO.builder()
                .id(votingSession.getId())
                .agenda(AgendaMapper.toDto(votingSession.getAgenda()))
                .status(getStatus(votingSession))
                .build();
    }

    public static List<VotingSessionResponseDTO> toDtoS(List<VotingSession> votingSession) {
        return votingSession.stream().map(VotingSessionMapper::toDto).toList();
    }

    private static StatusDTO getStatus(VotingSession votingSession) {
        Integer statusNumber = votingSession.getStatus().ordinal();
        return switch (votingSession.getStatus()) {
            case CLOSED -> new StatusDTO(statusNumber, "Fechado");
            case OPENED -> new StatusDTO(statusNumber, "Aberto");
        };
    }
}
