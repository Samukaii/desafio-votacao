package com.dbserver.votingchallenge.exceptions.agenda;

import com.dbserver.votingchallenge.exceptions.general.ConflictException;

public class AgendaVotingSessionNotOpenedException extends ConflictException {
    public AgendaVotingSessionNotOpenedException() {
        super("Nenhuma sessão de votação foi aberta para esta pauta ainda");
    }
}
