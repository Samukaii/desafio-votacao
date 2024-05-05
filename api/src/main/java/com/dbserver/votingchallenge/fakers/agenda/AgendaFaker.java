package com.dbserver.votingchallenge.fakers.agenda;

import com.dbserver.votingchallenge.domain.agenda.Agenda;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class AgendaFaker {
    static Faker faker = new Faker();

    static public Agenda createOne() {
        return Agenda.builder()
                .id(faker.number().randomDigit())
                .name(faker.lorem().sentence())
                .description(faker.lorem().paragraph())
                .build();
    }

    public static List<Agenda> createList(Integer count) {
        List<Agenda> allVotes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            allVotes.add(createOne());
        }

        return allVotes;
    }
}
