package com.dbserver.votingchallenge.exceptions.general;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
