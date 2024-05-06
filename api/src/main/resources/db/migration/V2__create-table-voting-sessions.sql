create table voting_sessions
(
    id        BIGSERIAL PRIMARY KEY UNIQUE                      NOT NULL,
    status    INTEGER                                           NOT NULL DEFAULT 0,
    agenda_id INTEGER REFERENCES agendas (id) ON DELETE CASCADE NOT NULL
);
