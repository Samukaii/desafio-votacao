package com.dbserver.votingchallenge.mappers.votingSession;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotingSessionMapper {
    public VotingSession toEntity(Agenda agenda) {
        return VotingSession.builder()
                .agenda(agenda)
                .status(VotingSessionStatus.OPENED)
                .build();
    }
}
