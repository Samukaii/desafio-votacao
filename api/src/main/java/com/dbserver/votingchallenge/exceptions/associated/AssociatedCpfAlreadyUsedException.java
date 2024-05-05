package com.dbserver.votingchallenge.exceptions.associated;

import com.dbserver.votingchallenge.exceptions.general.ConflictException;

public class AssociatedCpfAlreadyUsedException extends ConflictException {
    public AssociatedCpfAlreadyUsedException() {
        super("Este cpf já foi utilizado");
    }
}
