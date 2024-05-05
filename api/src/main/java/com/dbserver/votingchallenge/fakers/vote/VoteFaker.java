package com.dbserver.votingchallenge.fakers.vote;

import com.dbserver.votingchallenge.domain.vote.Vote;
import com.dbserver.votingchallenge.fakers.associated.AssociatedFaker;
import com.dbserver.votingchallenge.fakers.votingSession.VotingSessionFaker;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VoteFaker {
    private final Faker faker = new Faker();
    private final VotingSessionFaker votingSessionFaker;
    private final AssociatedFaker associatedFaker;

    public Vote createOne() {
        return Vote.builder()
                .id(faker.number().randomDigit())
                .favorable(faker.bool().bool())
                .associated(associatedFaker.createOne())
                .votingSession(votingSessionFaker.createOne())
                .build();
    }

    public List<Vote> createList(Integer count) {
        List<Vote> allVotes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            allVotes.add(createOne());
        }

        return allVotes;
    }
}
