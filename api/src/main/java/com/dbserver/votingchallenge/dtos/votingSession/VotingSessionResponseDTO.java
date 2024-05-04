package com.dbserver.votingchallenge.dtos.votingSession;

import com.dbserver.votingchallenge.dtos.agenda.AgendaResponseListDTO;
import com.dbserver.votingchallenge.dtos.general.StatusDTO;

public record VotingSessionResponseDTO(
        Integer id,
        AgendaResponseListDTO agenda,
        StatusDTO status
) {
}
