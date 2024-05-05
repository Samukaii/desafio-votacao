package com.dbserver.votingchallenge.fakers.agenda;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AgendaFaker {
    private final Faker faker = new Faker();

    public Agenda createOne() {
        return Agenda.builder()
                .id(faker.number().randomDigit())
                .name(faker.lorem().sentence())
                .description(faker.lorem().paragraph())
                .build();
    }

    public List<Agenda> createList(Integer count) {
        List<Agenda> allVotes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            allVotes.add(createOne());
        }

        return allVotes;
    }
}
