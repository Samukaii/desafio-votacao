package com.dbserver.votingchallenge.exceptions.votingSessions;

import com.dbserver.votingchallenge.exceptions.general.NotFoundException;

public class VotingSessionNotFoundException extends NotFoundException {
    public VotingSessionNotFoundException() {
        super("Sessão de votação não encontrada");
    }
}
