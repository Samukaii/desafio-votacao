package com.dbserver.votingchallenge.mappers.agenda;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.vote.VoteService;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.dtos.agenda.AgendaCreateDTO;
import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.dtos.general.StatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgendaMapper {
    private final VoteService voteService;

    public Agenda toEntity(AgendaCreateDTO dto) {
        return Agenda.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public AgendaResponseListDTO toDto(Agenda agenda) {
        return AgendaResponseListDTO.builder()
                .id(agenda.getId())
                .name(agenda.getName())
                .description(agenda.getDescription())
                .status(getStatus(agenda.getVotingSession()))
                .results(voteService.getResult(agenda.getVotingSession()))
                .build();
    }

    public List<AgendaResponseListDTO> toDtoS(List<Agenda> agendas) {
        return agendas.stream().map(this::toDto).toList();
    }

    private StatusDTO getStatus(VotingSession votingSession) {
        if(votingSession == null)
            return new StatusDTO(0, "Votações não iniciadas");

        Integer statusNumber = votingSession.getStatus().ordinal();
        return switch (votingSession.getStatus()) {
            case NOT_STARTED -> new StatusDTO(statusNumber, "Votações não iniciadas");
            case OPENED -> new StatusDTO(statusNumber, "Votações abertas");
            case ACCEPTED -> new StatusDTO(statusNumber, "Aceita");
            case REJECTED -> new StatusDTO(statusNumber, "Rejeitada");
            case DRAW -> new StatusDTO(statusNumber, "Empate");
        };
    }
}
