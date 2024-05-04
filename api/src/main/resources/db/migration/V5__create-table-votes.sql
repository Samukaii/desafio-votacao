create table votes
(
    id                BIGSERIAL PRIMARY KEY UNIQUE            NOT NULL,
    favorable         BOOLEAN                                 NOT NULL,
    associated_id     INTEGER REFERENCES associates (id)      not null,
    voting_session_id INTEGER REFERENCES voting_sessions (id) not null
);
