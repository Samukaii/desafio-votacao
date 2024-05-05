package com.dbserver.votingchallenge.exceptions.votingSessions;

import com.dbserver.votingchallenge.exceptions.general.ConflictException;

public class AgendaAlreadyOpenVotingSessionException extends ConflictException {
    public AgendaAlreadyOpenVotingSessionException() {
        super("Uma sessão de votação já foi aberta para esta pauta");
    }
}
