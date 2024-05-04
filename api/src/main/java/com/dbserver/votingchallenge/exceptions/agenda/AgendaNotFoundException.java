package com.dbserver.votingchallenge.exceptions.agenda;

public class AgendaNotFoundException extends RuntimeException {
    public AgendaNotFoundException() {
        super("Agenda not found");
    }
}
