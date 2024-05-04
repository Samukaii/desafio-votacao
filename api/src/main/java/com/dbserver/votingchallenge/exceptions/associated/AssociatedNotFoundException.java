package com.dbserver.votingchallenge.exceptions.associated;

public class AssociatedNotFoundException extends RuntimeException {
    public AssociatedNotFoundException() {
        super("Associated not found");
    }
}
