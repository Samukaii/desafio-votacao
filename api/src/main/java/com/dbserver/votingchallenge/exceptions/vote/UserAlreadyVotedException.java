package com.dbserver.votingchallenge.exceptions.vote;

public class UserAlreadyVotedException extends RuntimeException {
    public UserAlreadyVotedException() {
        super("User already voted");
    }
}
