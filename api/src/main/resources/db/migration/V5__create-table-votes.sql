create table votes
(
    id                BIGSERIAL PRIMARY KEY UNIQUE                              NOT NULL,
    favorable         BOOLEAN                                                   NOT NULL,
    associated_id     INTEGER REFERENCES associates (id)                        NOT NULL,
    voting_session_id INTEGER REFERENCES voting_sessions (id) ON DELETE CASCADE NOT NULL
);
