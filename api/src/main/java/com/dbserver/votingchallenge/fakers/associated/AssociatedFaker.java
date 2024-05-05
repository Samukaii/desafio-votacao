package com.dbserver.votingchallenge.fakers.associated;

import com.dbserver.votingchallenge.domain.associated.Associated;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class AssociatedFaker {
    static Faker faker = new Faker();

    static public Associated createOne() {
        return Associated.builder()
                .id(faker.number().randomDigit())
                .name(faker.name().name())
                .cpf(faker.number().digits(11))
                .build();
    }

    public static List<Associated> createList(Integer count) {
        List<Associated> allVotes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            allVotes.add(createOne());
        }

        return allVotes;
    }
}
