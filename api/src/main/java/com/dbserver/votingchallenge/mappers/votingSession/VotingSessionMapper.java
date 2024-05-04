package com.dbserver.votingchallenge.mappers.votingSession;

import com.dbserver.votingchallenge.domain.voting.VotingSession;
import com.dbserver.votingchallenge.dtos.general.StatusDTO;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResponseDTO;
import com.dbserver.votingchallenge.mappers.agenda.AgendaMapper;

import java.util.List;

public class VotingSessionMapper {
    public static VotingSessionResponseDTO toDto(VotingSession votingSession) {
        return new VotingSessionResponseDTO(
                votingSession.getId(),
                AgendaMapper.toDto(votingSession.getAgenda()),
                getStatus(votingSession)
        );
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
