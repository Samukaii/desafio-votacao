package com.dbserver.votingchallenge.exceptions.vote;

import com.dbserver.votingchallenge.exceptions.general.ConflictException;

public class UserAlreadyVotedException extends ConflictException {
    public UserAlreadyVotedException() {
        super("Este usuário já votou nesta pauta");
    }
}
