package com.dbserver.votingchallenge.exceptions.agenda;

import com.dbserver.votingchallenge.exceptions.general.ConflictException;

public class AgendaVotingSessionClosedException extends ConflictException {
    public AgendaVotingSessionClosedException() {
        super("O período de votação para esta pauta está encerrado");
    }
}
