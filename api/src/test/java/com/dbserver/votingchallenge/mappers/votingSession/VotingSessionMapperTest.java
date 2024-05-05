package com.dbserver.votingchallenge.mappers.votingSession;

import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.dtos.votingSession.VotingSessionResponseDTO;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.fakers.votingSession.VotingSessionFaker;
import com.dbserver.votingchallenge.mappers.agenda.AgendaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VotingSessionMapperTest {
    @Test
    void shallConvertToDTO() {
        VotingSession votingSession = VotingSessionFaker.createOne();
        String name = votingSession.getStatus() == VotingSessionStatus.CLOSED
                ? "Fechado" : "Aberto";

        VotingSessionResponseDTO dto = VotingSessionMapper.toDto(votingSession);

        assertEquals(dto.id(), votingSession.getId());
        assertEquals(dto.agenda(), AgendaMapper.toDto(votingSession.getAgenda()));

        assertEquals(dto.status().id(), votingSession.getStatus().ordinal());
        assertEquals(dto.status().name(), name);
    }

    @Test
    void shallConvertToDTOList() {
        List<VotingSession> votingSessions = VotingSessionFaker.createList(1);
        VotingSession firstVotingSession = votingSessions.get(0);

        String name = firstVotingSession.getStatus() == VotingSessionStatus.CLOSED
                ? "Fechado" : "Aberto";

        List<VotingSessionResponseDTO> dtoS = VotingSessionMapper.toDtoS(votingSessions);

        VotingSessionResponseDTO firstDto = dtoS.get(0);


        assertEquals(firstDto.id(), firstVotingSession.getId());
        assertEquals(firstDto.agenda(), AgendaMapper.toDto(firstVotingSession.getAgenda()));

        assertEquals(firstDto.status().id(), firstVotingSession.getStatus().ordinal());
        assertEquals(firstDto.status().name(), name);
    }
}
