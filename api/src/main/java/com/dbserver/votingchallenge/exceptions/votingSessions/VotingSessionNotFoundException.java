package com.dbserver.votingchallenge.exceptions.votingSessions;

public class VotingSessionNotFoundException extends RuntimeException {
    public VotingSessionNotFoundException() {
        super("Voting session not found");
    }
}
