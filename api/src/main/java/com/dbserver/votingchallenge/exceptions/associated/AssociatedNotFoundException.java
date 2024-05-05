package com.dbserver.votingchallenge.exceptions.associated;

import com.dbserver.votingchallenge.exceptions.general.NotFoundException;

public class AssociatedNotFoundException extends NotFoundException {
    public AssociatedNotFoundException() {
        super("Associado não encontrado");
    }
}
