package com.dbserver.votingchallenge.fakers.votingSession;

import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VotingSessionFaker {
    private final Faker faker = new Faker();
    private final AgendaFaker agendaFaker;

    public VotingSession createOne() {
        return VotingSession.builder()
                .id(faker.number().randomDigit())
                .status(VotingSessionStatus.OPENED)
                .agenda(agendaFaker.createOne())
                .build();
    }

    public List<VotingSession> createList(Integer count) {
        List<VotingSession> allVotes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            allVotes.add(createOne());
        }

        return allVotes;
    }
}
