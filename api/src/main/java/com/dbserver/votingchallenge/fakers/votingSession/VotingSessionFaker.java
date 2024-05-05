package com.dbserver.votingchallenge.fakers.votingSession;

import com.dbserver.votingchallenge.domain.votingSession.VotingSession;
import com.dbserver.votingchallenge.enums.VotingSessionStatus;
import com.dbserver.votingchallenge.fakers.agenda.AgendaFaker;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class VotingSessionFaker {
    static Faker faker = new Faker();

    public static VotingSession createOne() {
        return VotingSession.builder()
                .id(faker.number().randomDigit())
                .status(VotingSessionStatus.OPENED)
                .agenda(AgendaFaker.createOne())
                .build();
    }

    public static List<VotingSession> createList(Integer count) {
        List<VotingSession> allVotes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            allVotes.add(createOne());
        }

        return allVotes;
    }
}
