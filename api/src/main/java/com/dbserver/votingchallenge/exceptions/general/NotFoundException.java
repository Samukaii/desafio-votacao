package com.dbserver.votingchallenge.exceptions.general;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
