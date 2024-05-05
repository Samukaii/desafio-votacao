package com.dbserver.votingchallenge.fakers.vote;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.fakers.associated.AssociatedFaker;
import com.dbserver.votingchallenge.fakers.votingSession.VotingSessionFaker;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class VoteFaker {
    static Faker faker = new Faker();

    public static Vote createOne() {
        return Vote.builder()
                .id(faker.number().randomDigit())
                .favorable(faker.bool().bool())
                .associated(AssociatedFaker.createOne())
                .votingSession(VotingSessionFaker.createOne())
                .build();
    }

    public static List<Vote> createList(Integer count) {
        List<Vote> allVotes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            allVotes.add(createOne());
        }

        return allVotes;
    }
}
