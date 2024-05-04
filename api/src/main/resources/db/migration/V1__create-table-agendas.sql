create table agendas
(
    id          BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    name        VARCHAR(250)                 NOT NULL,
    description VARCHAR(500)                 NOT NULL
);
