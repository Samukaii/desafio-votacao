package com.dbserver.votingchallenge.exceptions.associated;

public class AssociatedCpfAlreadyUsedException extends RuntimeException {
    public AssociatedCpfAlreadyUsedException() {
        super("Associated cpf already used");
    }
}
