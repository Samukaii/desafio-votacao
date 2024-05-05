package com.dbserver.votingchallenge.exceptions.agenda;

import com.dbserver.votingchallenge.exceptions.general.NotFoundException;

public class AgendaNotFoundException extends NotFoundException {
    public AgendaNotFoundException() {
        super("Pauta não encontrada");
    }
}
